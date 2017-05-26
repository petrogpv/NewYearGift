package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;
import com.petro.newyeargift.gift.confection.components.Filling;
import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 25.05.2017.
 */
public class Candy extends Confection {

    private Filling filling;

    public Candy(String name, Glaze glaze, Double weight, Integer sugarPercentage, Integer sugarValue, BigDecimal price, Filling filling) {
        super(name, glaze, weight, sugarPercentage, sugarValue, price);
        this.filling = filling;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }
}
