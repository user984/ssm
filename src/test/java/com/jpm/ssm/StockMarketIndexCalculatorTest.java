package com.jpm.ssm;

import com.jpm.ssm.model.OrderType;
import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;
import com.jpm.ssm.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StockMarketIndexCalculatorTest {

    private StockMarketIndexCalculator smic = new StockMarketIndexCalculator();

    private Map<String, Stock> marketStocks = new HashMap<>();

    @Before
    public void setUp() {
        Trade t1 = new Trade(1, 100L, 1000000L, new BigDecimal("5"), OrderType.BUY);
        Trade t2 = new Trade(2, 100L, 1000000L, new BigDecimal("6"), OrderType.SELL);
        Trade t3 = new Trade(3, 100L, 1000000L, new BigDecimal("6"), OrderType.BUY);
        Trade t4 = new Trade(4, 100L, 1000000L, new BigDecimal("7"), OrderType.SELL);
        Trade t5 = new Trade(5, 100L, 1000000L, new BigDecimal("7"), OrderType.BUY);
        Trade t6 = new Trade(6, 100L, 1000000L, new BigDecimal("8"), OrderType.SELL);
        Trade t7 = new Trade(7, 100L, 1000000L, new BigDecimal("10"), OrderType.BUY);
        Trade t8 = new Trade(8, 100L, 1000000L, new BigDecimal("11"), OrderType.SELL);
        Trade t9 = new Trade(7, 100L, 1000000L, new BigDecimal("11"), OrderType.BUY);
        Trade t10 = new Trade(8, 100L, 1000000L, new BigDecimal("12"), OrderType.SELL);


        marketStocks.put("TEA", new Stock(1, "TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal("100"), List.of(t1, t2)));
        marketStocks.put("POP", new Stock(2, "POP", StockType.COMMON, new BigDecimal("8"), BigDecimal.ZERO,
                new BigDecimal("100"), List.of(t3, t4)));
        marketStocks.put("ALE", new Stock(3, "ALE", StockType.COMMON, new BigDecimal("23"),
                BigDecimal.ZERO, new BigDecimal("60"), List.of(t5, t6)));
        marketStocks.put("GIN", new Stock(4, "GIN", StockType.PREFERRED, new BigDecimal("8"),
                new BigDecimal("0.02"), new BigDecimal("100"), List.of(t7, t8)));
        marketStocks.put("JOE", new Stock(5, "JOE", StockType.COMMON, new BigDecimal("13"), BigDecimal.ZERO,
                new BigDecimal("250"), List.of(t9, t10)));

    }


    @Test
    public void getGBCEAllShareIndex() {
        assertEquals(new BigDecimal("8.499290"), smic.getGBCEAllShareIndex(marketStocks));
    }
}