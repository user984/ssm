package com.jpm.ssm.model;


import java.math.BigDecimal;
import java.util.Objects;

public class Trade
{
    private long tradeId;
    private long quantity;
    private long epoch;

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    private BigDecimal price;
    private OrderType orderType;

    public Trade(long tradeId, long quantity, long epoch, BigDecimal price, OrderType orderType) {
        this.tradeId = tradeId;
        this.quantity = quantity;
        this.epoch = epoch;
        this.price = price;
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return tradeId == trade.tradeId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tradeId);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", quantity=" + quantity +
                ", epoch=" + epoch +
                ", price=" + price +
                ", orderType=" + orderType +
                '}';
    }
}
