package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;
import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Bar extends Confection {

        private boolean consistNuts;


    public Bar(String name, Glaze glaze, Double weight, Integer sugarPercentage, Integer sugarValue, BigDecimal price, boolean consistNuts) {
        super(name, glaze, weight, sugarPercentage, sugarValue, price);
        this.consistNuts = consistNuts;
    }

    public boolean isConsistNuts() {
        return consistNuts;
    }

    public void setConsistNuts(boolean consistNuts) {
        this.consistNuts = consistNuts;
    }
}
