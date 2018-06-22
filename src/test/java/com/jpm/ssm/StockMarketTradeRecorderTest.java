package com.jpm.ssm;

import com.jpm.ssm.model.OrderType;
import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;
import com.jpm.ssm.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StockMarketTradeRecorderTest {

    private StockMarketTradeRecorder smtr = new StockMarketTradeRecorder();

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


        List<Trade> teaTrades = new ArrayList<>();
        teaTrades.add(t1);
        teaTrades.add(t2);
        marketStocks.put("TEA", new Stock(1, "TEA", StockType.COMMON, BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal("100"), teaTrades));

        List<Trade> popTrades = new ArrayList<>();
        popTrades.add(t3);
        popTrades.add(t4);
        marketStocks.put("POP", new Stock(2, "POP", StockType.COMMON, new BigDecimal("8"), BigDecimal.ZERO,
                new BigDecimal("100"), popTrades));


        List<Trade> aleTrades = new ArrayList<>();
        aleTrades.add(t5);
        aleTrades.add(t6);
        marketStocks.put("ALE", new Stock(3, "ALE", StockType.COMMON, new BigDecimal("23"),
                BigDecimal.ZERO, new BigDecimal("60"), aleTrades));


        List<Trade> ginTrades = new ArrayList<>();
        ginTrades.add(t7);
        ginTrades.add(t8);
        marketStocks.put("GIN", new Stock(4, "GIN", StockType.PREFERRED, new BigDecimal("8"),
                new BigDecimal("0.02"), new BigDecimal("100"), ginTrades));

        List<Trade> joeTrades = new ArrayList<>();
        joeTrades.add(t9);
        joeTrades.add(t10);
        marketStocks.put("JOE", new Stock(5, "JOE", StockType.COMMON, new BigDecimal("13"), BigDecimal.ZERO,
                new BigDecimal("250"), joeTrades));

    }


    @Test
    public void recordTradeSymbolNotPresent() {
        assertFalse(smtr.recordTrade(new BigDecimal("1.0"), "JOA", 10000L, OrderType.BUY,
                30000000, 2, marketStocks));
    }

    @Test
    public void recordTradeSymbolPresent() {
        assertTrue(smtr.recordTrade(new BigDecimal("1.0"), "GIN", 10000L, OrderType.BUY,
                30000000, 2, marketStocks));
        assertEquals(3, marketStocks.get("GIN").getTradeList().size());
    }
}