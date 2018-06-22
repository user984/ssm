package com.jpm.ssm;

import com.jpm.ssm.model.OrderType;
import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class StockMarketTradeRecorder {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean recordTrade(BigDecimal price, String symbol, long qty, OrderType ot, long timestamp, long tradeId,
                               Map<String, Stock> marketStocks) {
        if (marketStocks.containsKey(symbol)) {
            Stock s = marketStocks.get(symbol);
            List<Trade> trades = s.getTradeList();
            trades.add(new Trade(tradeId, qty, timestamp, price, ot));
            s.setTradeList(trades);
            marketStocks.put(symbol, s);
            return true;
        } else {
            logger.error("Symbol not traded in the stock market");
            return false;
        }
    }
}
