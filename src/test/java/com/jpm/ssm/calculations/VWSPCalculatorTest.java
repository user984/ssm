package com.jpm.ssm.calculations;

import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;
import com.jpm.ssm.model.Trade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class VWSPCalculatorTest {
    @InjectMocks
    private VWSPCalculator vwspCalculator;

    private Stock stock0Trades;

    @Mock
    private Stock stock;

    @Mock
    private Stock stockWithRecentAndOldTrades, stockWithOldTrades;

    private List<Trade> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        vwspCalculator = new VWSPCalculator();
        stock0Trades = new Stock(1, "ABC", StockType.COMMON, new BigDecimal("3"),
                new BigDecimal("0"), new BigDecimal("100"), emptyList);

        //  create mock and define behaviour
        Trade trade1 = Mockito.mock(Trade.class);
        Trade trade2 = Mockito.mock(Trade.class);
        // define return value for method getUniqueId()
        when(trade1.getQuantity()).thenReturn(100L);
        when(trade1.getPrice()).thenReturn(BigDecimal.valueOf(3d));

        when(trade2.getQuantity()).thenReturn(200L);
        when(trade2.getPrice()).thenReturn(BigDecimal.valueOf(5d));

        List<Trade> trades = Arrays.asList(trade1, trade2);
        when(stock.getTradeList()).thenReturn(trades);

        Trade oldTrade1 = Mockito.mock(Trade.class);
        Trade oldTrade2 = Mockito.mock(Trade.class);
        Trade recentTrade3 = Mockito.mock(Trade.class);
        Trade recentTrade4 = Mockito.mock(Trade.class);

        when(oldTrade1.getQuantity()).thenReturn(100L);
        when(oldTrade1.getPrice()).thenReturn(BigDecimal.valueOf(3d));
        when(oldTrade1.getEpoch()).thenReturn(2000L);

        when(oldTrade2.getQuantity()).thenReturn(200L);
        when(oldTrade2.getPrice()).thenReturn(BigDecimal.valueOf(5d));
        when(oldTrade2.getEpoch()).thenReturn(9000L);

        when(recentTrade3.getQuantity()).thenReturn(200L);
        when(recentTrade3.getPrice()).thenReturn(BigDecimal.valueOf(5d));
        when(recentTrade3.getEpoch()).thenReturn(Instant.now().getEpochSecond() - 15);

        when(recentTrade4.getQuantity()).thenReturn(1000L);
        when(recentTrade4.getPrice()).thenReturn(BigDecimal.valueOf(5d));
        when(recentTrade4.getEpoch()).thenReturn(Instant.now().getEpochSecond() - 5);

        List<Trade> oldTrades = Arrays.asList(oldTrade1, oldTrade2);
        when(stockWithOldTrades.getTradeList()).thenReturn(oldTrades);

        List<Trade> mixOfTrades = Arrays.asList(oldTrade1, recentTrade3, recentTrade4);
        when(stockWithRecentAndOldTrades.getTradeList()).thenReturn(mixOfTrades);
    }


    @Test
    public void calculateVWSPSingleStock() {
        assertEquals(BigDecimal.ZERO, vwspCalculator.calculate(stock0Trades));
        assertEquals(new BigDecimal("4.3"), vwspCalculator.calculate(stock));
    }

    @Test
    public void calculateVWSPSingleStockWithDelayButAllOldTrades() {
        assertEquals(BigDecimal.ZERO, vwspCalculator.calculate(stockWithOldTrades, 5));
    }

    @Test
    public void calculateVWSPSingleStockWithMixOfTrades() {
        assertEquals(new BigDecimal("5.0"), vwspCalculator.calculate(stockWithRecentAndOldTrades, 5));
    }

}