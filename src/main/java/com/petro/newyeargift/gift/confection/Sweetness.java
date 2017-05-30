package com.petro.newyeargift.gift.confection;

import java.math.BigDecimal;

/**
 * Created by Администратор on 26.05.2017.
 */
public interface Sweetness {
    String getName();
    Double getWeight();
    Double getSugarPercentage();
    Double getSugarValue();
    BigDecimal getPrice();
}
