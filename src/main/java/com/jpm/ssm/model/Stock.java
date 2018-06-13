package com.jpm.ssm.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Stock
{
    private long stockId;
    private String symbol;
    private StockType stockType;
    private BigDecimal lastDividend;
    private BigDecimal fixedDividend;
    private BigDecimal parValue;
    private List<Trade> tradeList;

    public Stock(long stockId, String symbol, StockType stockType, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue, List<Trade> tradeList) {
        this.stockId = stockId;
        this.symbol = symbol;
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.tradeList = tradeList;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(BigDecimal lastDividend) {
        this.lastDividend = lastDividend;
    }

    public BigDecimal getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(BigDecimal fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<Trade> tradeList) {
        this.tradeList = tradeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return stockId == stock.stockId &&
                Objects.equals(symbol, stock.symbol) &&
                stockType == stock.stockType &&
                Objects.equals(lastDividend, stock.lastDividend) &&
                Objects.equals(fixedDividend, stock.fixedDividend) &&
                Objects.equals(parValue, stock.parValue) &&
                Objects.equals(tradeList, stock.tradeList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stockId, symbol, stockType, lastDividend, fixedDividend, parValue, tradeList);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", symbol='" + symbol + '\'' +
                ", stockType=" + stockType +
                ", lastDividend=" + lastDividend +
                ", fixedDividend=" + fixedDividend +
                ", parValue=" + parValue +
                ", tradeList=" + tradeList +
                '}';
    }
}
