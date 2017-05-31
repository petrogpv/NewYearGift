package com.petro.newyeargift.controller.factories;

import com.petro.newyeargift.controller.EnumNotFoundException;
import com.petro.newyeargift.gift.confection.Sweetness;

/**
 * Created by Администратор on 27.05.2017.
 */
public interface SweetnessFactory {
    Sweetness create(String[] fields) throws EnumNotFoundException;
}
