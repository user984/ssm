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

public class DividendYieldCalculatorTest {

    private DividendYieldCalculator dyc;
    private Stock commonStock, commonStockZeroLastDiv;
    private Stock preferredStock;
    private List<Trade> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        dyc = new DividendYieldCalculator();

        commonStock = new Stock(1, "ABC", StockType.COMMON, new BigDecimal("3"),
                new BigDecimal("0"), new BigDecimal("100"), emptyList);

        commonStockZeroLastDiv = new Stock(3, "ABC", StockType.COMMON, new BigDecimal("0"),
                new BigDecimal("0"), new BigDecimal("100"), emptyList);

        preferredStock = new Stock(2, "ABC", StockType.PREFERRED, new BigDecimal("8"),
                new BigDecimal("0.02"), new BigDecimal("100"), emptyList);
    }


    @Test
    public void calculateCommonStockDividendYield() {

        assertEquals(new BigDecimal("-1"), dyc.calculate("0000000000", commonStock));
        assertEquals(new BigDecimal("-1"), dyc.calculate("0.00", commonStock));
        assertEquals(new BigDecimal("-1"), dyc.calculate("0", commonStock));

        assertEquals(new BigDecimal("0.000000"), dyc.calculate("100", commonStockZeroLastDiv));
        assertEquals(new BigDecimal("0.100000"), dyc.calculate("30", commonStock));
        assertEquals(new BigDecimal("0.030000"), dyc.calculate("100", commonStock));
    }

    @Test
    public void calculatePreferredStockDividendYield() {
        assertEquals(new BigDecimal("0.020000"), dyc.calculate("100", preferredStock));
    }

}