package com.jpm.ssm.calculations;

import com.jpm.ssm.model.Stock;
import com.jpm.ssm.model.StockType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DividendYieldCalculator
{
    private static final int PRECISION = 6;

    public BigDecimal calculate(String price, Stock s) {
        BigDecimal yield = BigDecimal.ZERO;

        if (s.getStockType() == StockType.COMMON) {
            // Common
            yield = s.getLastDividend().divide(new BigDecimal(price), PRECISION, RoundingMode.HALF_UP);

        } else if (s.getStockType() == StockType.PREFERRED) {
            // Preferred
            yield = (s.getFixedDividend().multiply(s.getParValue())).divide(new BigDecimal(price), PRECISION,
                    RoundingMode.HALF_UP);
        }
        return yield;
    }
}
