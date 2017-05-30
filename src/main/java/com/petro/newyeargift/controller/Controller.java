package com.petro.newyeargift.controller;

import com.petro.newyeargift.controller.factories.FactorySelector;
import com.petro.newyeargift.controller.factories.SweetnessFactory;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.gift.confection.SweetnessType;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Controller {
    public void process() throws EnumNotFoundException {
//                            0       1     2   3   4     5
        String testCandy = "Sosulka SOLID NONE 5.5 30.3 2.2";
        SweetnessFactory sweetnessFactory= FactorySelector.getInstance()
                .getSweetnessFactory(SweetnessType.CANDY);

        Sweetness s = null;
        try {
            s = sweetnessFactory.create(testCandy);
        } catch (EnumNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(s.toString());
        Utils utils= new Utils();
        utils.readFromFile();

    }
}
