package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;
import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Waffle extends Confection {

    private String flavour;

    public Waffle(String name, Glaze glaze, Double weight, Integer sugarPercentage, Integer sugarValue, BigDecimal price, String flavour) {
        super(name, glaze, weight, sugarPercentage, sugarValue, price);
        this.flavour = flavour;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
}
