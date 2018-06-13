package com.jpm.ssm;

import com.jpm.ssm.calculations.DividendYieldCalculator;
import com.jpm.ssm.calculations.PECalculator;
import com.jpm.ssm.calculations.VWSPCalculator;
import com.jpm.ssm.model.OrderType;
import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;
import com.jpm.ssm.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class StockMarket
{
    public Map<String, Stock> getMarketStocks() {
        return marketStocks;
    }

    public void setMarketStocks(Map<String, Stock> marketStocks) {
        this.marketStocks = marketStocks;
    }

    private Map<String, Stock> marketStocks;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DividendYieldCalculator dyc = new DividendYieldCalculator();
    private PECalculator pec = new PECalculator();
    private VWSPCalculator vwsp = new VWSPCalculator();

    public StockMarket() {

        marketStocks = new HashMap<>();
        marketStocks.put("TEA", new Stock(1, "TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("100"), new ArrayList<>()));
        marketStocks.put("POP", new Stock(2, "POP", StockType.COMMON, new BigDecimal("8"), BigDecimal.ZERO, new BigDecimal("100"), new ArrayList<>()));
        marketStocks.put("ALE", new Stock(3, "ALE", StockType.COMMON, new BigDecimal("23"), BigDecimal.ZERO, new BigDecimal("60"), new ArrayList<>()));
        marketStocks.put("GIN", new Stock(4, "GIN", StockType.PREFERRED, new BigDecimal("8"), new BigDecimal("0.02"), new BigDecimal("100"), new ArrayList<>()));
        marketStocks.put("JOE", new Stock(5, "JOE", StockType.COMMON, new BigDecimal("13"), BigDecimal.ZERO, new BigDecimal("250"), new ArrayList<>()));


    }

    public void compute(String symbol, String price) {
        if (marketStocks.containsKey(symbol.trim().toUpperCase())) {
            BigDecimal yield = dyc.calculate(price, getMarketStocks().get(symbol));
            BigDecimal peratio = pec.calculate(price, getMarketStocks().get(symbol));
            logger.info("Yield is " + yield);
            logger.info("PE ratio is " + peratio);
            // no trades recorded, so this needs be be commented.
            // BigDecimal vwStockPrice = vwsp.calculate(getMarketStocks().get(symbol), 5);
            BigDecimal gbceAllShareIndexValue = getGBCEAllShareIndex();
            System.out.println(gbceAllShareIndexValue);
        } else {
            logger.error("Symbol not traded in the stock market");
        }
    }

    public void recordTrade(BigDecimal price, String symbol, long qty, OrderType ot, long timestamp, long tradeId) {
        if(marketStocks.containsKey(symbol)) {
            Stock s = marketStocks.get(symbol);
            List<Trade> trades = s.getTradeList();
            trades.add(new Trade(tradeId, qty, timestamp, price, ot));
            s.setTradeList(trades);
            marketStocks.put(symbol, s);
        } else {
            logger.error("Symbol not traded in the stock market");
        }
    }

    public BigDecimal getGBCEAllShareIndex() {
        BigDecimal vwspProduct = BigDecimal.ONE;
        for (Map.Entry<String, Stock> entry: getMarketStocks().entrySet()) {
            // vwspProduct = vwspProduct.multiply(new BigDecimal("4"));
            vwspProduct = vwspProduct.multiply(vwsp.calculate(entry.getValue()));
        }
        BigDecimal mean = new BigDecimal(Math.pow(vwspProduct.doubleValue(), 1.0 / getMarketStocks().size()), MathContext.DECIMAL64);
        return mean;

    }
}
