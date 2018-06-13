package com.jpm.ssm.calculations;

import com.jpm.ssm.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PECalculator
{
    private static final int PRECISION = 6;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BigDecimal calculate(String price, Stock s) {
        BigDecimal peratio = BigDecimal.ZERO;

        if (s.getLastDividend().compareTo(BigDecimal.ZERO) <= 0) {
            // Perhaps throw an exception as well...
            logger.error("Cannot calculate PE Ratio due to 0 dividend");
        } else {
            BigDecimal pr = new BigDecimal(price);
            peratio = pr.divide(s.getLastDividend(), PRECISION, RoundingMode.HALF_UP);
        }
        return peratio;
    }
}
