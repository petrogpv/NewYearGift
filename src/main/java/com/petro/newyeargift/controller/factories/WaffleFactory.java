package com.petro.newyeargift.controller.factories;

import com.petro.newyeargift.controller.EnumNotFoundException;
import com.petro.newyeargift.controller.Utils;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.gift.confection.components.Flavour;
import com.petro.newyeargift.gift.confection.components.Glaze;
import com.petro.newyeargift.gift.confection.entity.Waffle;

/**
 * Created by Администратор on 27.05.2017.
 */
public class WaffleFactory implements SweetnessFactory {

    @Override
    public Sweetness create(String[] fields) throws EnumNotFoundException {
//        String [] fields = Utils.parseLine(line);
        Waffle.Builder builder =  Waffle.getBuilder();
        builder
                .setName(fields[0])
                .setGlaze((Glaze)Utils.convertStringIntoEnum(Glaze.class, fields[1]))
                .setFlavor((Flavour) Utils.convertStringIntoEnum(Flavour.class, fields[2]))
                .setWeight(Utils.convertStringIntoDouble(fields[3]))
                .setSugarPercentage(Utils.convertStringIntoDouble(fields[4]))
                .setPrice(Utils.convertStringIntoBigDecimal(fields[5]))
        ;

        return builder.build();
    }
}
