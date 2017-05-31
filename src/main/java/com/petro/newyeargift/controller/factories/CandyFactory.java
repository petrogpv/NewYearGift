package com.petro.newyeargift.controller.factories;

import com.petro.newyeargift.controller.EnumNotFoundException;
import com.petro.newyeargift.controller.Utils;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.gift.confection.components.Filling;
import com.petro.newyeargift.gift.confection.components.Glaze;
import com.petro.newyeargift.gift.confection.entity.Candy;

/**
 * Created by Администратор on 27.05.2017.
 */
public class CandyFactory implements SweetnessFactory {

    @Override
    public Sweetness create(String[] fields) throws EnumNotFoundException {
//        String [] fields = Utils.parseLine(line);
        Candy.Builder builder = Candy.getBuilder();
        builder
                .setName(fields[0])
                .setGlaze((Glaze)Utils.convertStringIntoEnum(Glaze.class, fields[1]))
                .setFilling((Filling)Utils.convertStringIntoEnum(Filling.class, fields[2]))
                .setWeight(Utils.convertStringIntoDouble(fields[3]))
                .setSugarPercentage(Utils.convertStringIntoDouble(fields[4]))
                .setPrice(Utils.convertStringIntoBigDecimal(fields[5]))
        ;

        return builder.build();
    }
}
