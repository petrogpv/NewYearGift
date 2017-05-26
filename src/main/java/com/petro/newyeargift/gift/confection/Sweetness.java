package com.petro.newyeargift.gift.confection;

import com.petro.newyeargift.gift.confection.components.Glaze;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public interface Sweetness {
    String getName();

    void setName(String name);

    Glaze getGlaze();

    void setGlaze(Glaze glaze);

    Double getWeight();

    void setWeight(Double weight);

    Integer getSugarPercentage();

    void setSugarPercentage(Integer sugarPercentage);

    Integer getSugarValue();

    void setSugarValue(Integer sugarValue);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);
}
