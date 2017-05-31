package com.petro.newyeargift.gift.confection;

import com.petro.newyeargift.controller.Utils;
import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public abstract class Confection implements Sweetness {

    protected String name;
    protected Glaze glaze;
    protected Double weight;
    protected Double sugarPercentage;
    protected Double sugarValue;
    protected BigDecimal price;

    protected Confection(){}

    public String getName() {
        return name;
    }

    public Glaze getGlaze() {
        return glaze;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getSugarPercentage() {
        return sugarPercentage;
    }

    public Double getSugarValue() {
        return sugarValue;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public abstract class Builder <T extends Builder>{

        protected Builder(){}

        public T setName(String name) {
            Confection.this.name = name;
            return (T)this;
        }

        public T setGlaze(Glaze glaze) {
            Confection.this.glaze = glaze;
            return (T)this;
        }

        public T setWeight(Double weight) {
            Confection.this.weight = weight;
            return (T)this;
        }

        public T setSugarPercentage(Double sugarPercentage) {
            Confection.this.sugarPercentage = sugarPercentage;
            return (T)this;
        }

        public T setPrice (BigDecimal price) {
            Confection.this.price = price;
            return (T)this;
        }
        public Confection build(){
            Double sugarValue = (sugarPercentage/100)*weight;
            Confection.this.sugarValue =  Utils.roundDouble(sugarValue);
            return Confection.this;
        }

    }

}
