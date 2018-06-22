package com.jpm.ssm;

import com.jpm.ssm.calculations.DividendYieldCalculator;
import com.jpm.ssm.calculations.PECalculator;
import com.jpm.ssm.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


class StockMarket {
    // Stock Market is created as a Singleton.
    private StockMarket() {
    }

    private static class SingletonHelper {
        private static final StockMarket INSTANCE = new StockMarket();
    }

    static StockMarket getInstance() {
        return SingletonHelper.INSTANCE;
    }

    void initialise() {
        marketStocks = new HashMap<>();
        smic = new StockMarketIndexCalculator();
        smtr = new StockMarketTradeRecorder();
    }


    public Map<String, Stock> getMarketStocks() {
        return marketStocks;
    }

    public void setMarketStocks(Map<String, Stock> marketStocks) {
        this.marketStocks = marketStocks;
    }

    private Map<String, Stock> marketStocks;

    private StockMarketIndexCalculator smic;

    private StockMarketTradeRecorder smtr;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DividendYieldCalculator dyc = new DividendYieldCalculator();
    private PECalculator pec = new PECalculator();


    void compute(String symbol, String price) {
        if (marketStocks.containsKey(symbol.trim().toUpperCase())) {
            BigDecimal yield = dyc.calculate(price, getMarketStocks().get(symbol));
            BigDecimal peratio = pec.calculate(price, getMarketStocks().get(symbol));
            logger.info("Yield is " + yield);
            logger.info("PE ratio is " + peratio);
            //BigDecimal gbceAllShareIndexValue = smic.getGBCEAllShareIndex(getMarketStocks());
            //logger.info("GBCE All share index value =" + gbceAllShareIndexValue);
        } else {
            logger.error("Symbol not traded in the stock market");
        }
    }
}
