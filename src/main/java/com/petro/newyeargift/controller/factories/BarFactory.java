package com.petro.newyeargift.controller.factories;

import com.petro.newyeargift.controller.EnumNotFoundException;
import com.petro.newyeargift.controller.Utils;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.gift.confection.components.Filling;
import com.petro.newyeargift.gift.confection.components.Flavour;
import com.petro.newyeargift.gift.confection.components.Glaze;
import com.petro.newyeargift.gift.confection.entity.Bar;

/**
 * Created by Администратор on 27.05.2017.
 */
public class BarFactory implements SweetnessFactory {

    @Override
    public Sweetness create(String[] fields) throws EnumNotFoundException {
//        String [] fields = Utils.parseLine(line);
        Bar.Builder builder = Bar.getBuilder();
        builder
                .setName(fields[0])
                .setGlaze((Glaze)Utils.convertStringIntoEnum(Glaze.class, fields[1]))
                .setFlavor(Utils.convertStringYesNoToBoolean(fields[2]))
                .setWeight(Utils.convertStringIntoDouble(fields[3]))
                .setSugarPercentage(Utils.convertStringIntoDouble(fields[4]))
                .setPrice(Utils.convertStringIntoBigDecimal(fields[5]))
        ;
        return builder.build();
    }
}
