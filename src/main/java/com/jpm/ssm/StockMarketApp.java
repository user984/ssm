package com.jpm.ssm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockMarketApp {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String... args) {
        new StockMarketApp().run(args);
    }

    private void run(String... args) {
        logger.info("Starting simple stock market application");
        logger.info(args[0] + ":" + args[1]);
        StockMarket sm = new StockMarket();
        sm.compute(args[0], args[1]);

        logger.info("Finishing simple stock market application");
    }

}
