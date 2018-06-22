package com.jpm.ssm.calculations;

import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;
import com.jpm.ssm.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PECalculatorTest {
    private PECalculator peCalculator;
    private Stock commonStock, commonStockZeroLastDiv;
    private Stock preferredStock;
    private List<Trade> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        peCalculator = new PECalculator();

        commonStock = new Stock(1, "ABC", StockType.COMMON, new BigDecimal("3"),
                new BigDecimal("0"), new BigDecimal("100"), emptyList);

        commonStockZeroLastDiv = new Stock(3, "ABC", StockType.COMMON, new BigDecimal("0"),
                new BigDecimal("0"), new BigDecimal("100"), emptyList);

        preferredStock = new Stock(2, "ABC", StockType.PREFERRED, new BigDecimal("8"),
                new BigDecimal("0.02"), new BigDecimal("100"), emptyList);
    }


    @Test
    public void calculatePERatio() {
        assertEquals(new BigDecimal("-1"), peCalculator.calculate("100", commonStockZeroLastDiv));

        assertEquals(new BigDecimal("0.000000"), peCalculator.calculate("0", commonStock));
        assertEquals(new BigDecimal("33.333333"), peCalculator.calculate("100", commonStock));
        assertEquals(new BigDecimal("25.000000"), peCalculator.calculate("200", preferredStock));

    }
}