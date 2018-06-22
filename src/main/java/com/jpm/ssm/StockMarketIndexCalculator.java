package com.jpm.ssm;

import com.jpm.ssm.calculations.VWSPCalculator;
import com.jpm.ssm.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

class StockMarketIndexCalculator {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private VWSPCalculator vwsp = new VWSPCalculator();

    BigDecimal getGBCEAllShareIndex(Map<String, Stock> marketStocks) {
        BigDecimal vwspProduct = BigDecimal.ONE;
        for (Map.Entry<String, Stock> entry : marketStocks.entrySet()) {
            Stock st = entry.getValue();
            BigDecimal vwspPerStock = vwsp.calculate(st);
            logger.info("VWSP is " + vwspPerStock);
            vwspProduct = vwspProduct.multiply(vwspPerStock);
        }
        logger.info("VWSP Product is " + vwspProduct);
        BigDecimal geometricMean = new BigDecimal(Math.pow(vwspProduct.doubleValue(), 1.0 / marketStocks.size())).setScale(6, RoundingMode.HALF_UP);
        logger.info("Geometric mean is =" + geometricMean);
        return geometricMean;
    }
}
