package com.deepcopy.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class Complex {

    private Man man;
    private Map<Type, Simple> map;
    private BigDecimal bigDecimal;
    private LocalDateTime localDateTime;

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public Map<Type, Simple> getMap() {
        return map;
    }

    public void setMap(Map<Type, Simple> map) {
        this.map = map;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "man=" + man +
                ", map=" + map +
                ", bigDecimal=" + bigDecimal +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
