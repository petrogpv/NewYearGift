package com.petro.newyeargift.gift.confection;

import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public abstract class Confection implements Sweetness {

    private String name;
    private Glaze glaze;
    private Double weight;
    private Integer sugarPercentage;
    private Integer sugarValue;
    private BigDecimal price;

    public Confection(String name,
                      Glaze glaze,
                      Double weight,
                      Integer sugarPercentage,
                      Integer sugarValue,
                      BigDecimal price) {
        this.name = name;
        this.glaze = glaze;
        this.weight = weight;
        this.sugarPercentage = sugarPercentage;
        this.sugarValue = sugarValue;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Glaze getGlaze() {
        return glaze;
    }

    public void setGlaze(Glaze glaze) {
        this.glaze = glaze;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getSugarPercentage() {
        return sugarPercentage;
    }

    public void setSugarPercentage(Integer sugarPercentage) {
        this.sugarPercentage = sugarPercentage;
    }

    public Integer getSugarValue() {
        return sugarValue;
    }

    public void setSugarValue(Integer sugarValue) {
        this.sugarValue = sugarValue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
