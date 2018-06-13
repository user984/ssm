package com.jpm.ssm.calculations;

import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.Trade;
import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class VWSPCalculator
{
    private static final int PRECISION = 6;

    public BigDecimal calculate(Stock stock, long delay)
    {
        BigDecimal vwsp = BigDecimal.ZERO;
        List<Trade> tradeList = stock.getTradeList();
        List<Trade> filteredTradeList = tradeList.stream().filter(trade -> Instant.now().getEpochSecond() -
                trade.getEpoch() <= (delay * 60)).collect(Collectors.toList());
        if(filteredTradeList.size() == 0) {
            return vwsp;
        }

        BigDecimal priceTotal = BigDecimal.ZERO;
        long quantityTotal = 0L;
        for(Trade ftList : filteredTradeList) {
            BigDecimal price = ftList.getPrice();
            long quantity = ftList.getQuantity();
            priceTotal = priceTotal.add(price.multiply(new BigDecimal(quantity)));
            quantityTotal += quantity;
        }
        vwsp = priceTotal.divide(new BigDecimal(quantityTotal), RoundingMode.HALF_UP);
        return vwsp;
    }

    public BigDecimal calculate(Stock stock)
    {
        BigDecimal vwsp = BigDecimal.ZERO;
        List<Trade> tradeList = stock.getTradeList();
        if(tradeList.size() == 0) {
            return vwsp;
        }

        BigDecimal priceTotal = BigDecimal.ZERO;
        long quantityTotal = 0L;
        for(Trade trade : tradeList) {
            BigDecimal price = trade.getPrice();
            long quantity = trade.getQuantity();
            priceTotal = priceTotal.add(price.multiply(new BigDecimal(quantity)));
            quantityTotal += quantity;
        }
        vwsp = priceTotal.divide(new BigDecimal(quantityTotal), RoundingMode.HALF_UP);
        return vwsp;
    }
}
