package com.petro.newyeargift.controller.factories;

import com.petro.newyeargift.gift.confection.SweetnessType;

/**
 * Created by Администратор on 27.05.2017.
 */
public class FactorySelector {
    private static FactorySelector factorySelector;
    private FactorySelector() {
    }

    public static FactorySelector getInstance(){
        if(factorySelector == null){
            return new FactorySelector();
        }
        return factorySelector;
    }

    public SweetnessFactory getSweetnessFactory(SweetnessType st){
        switch(st) {
            case CANDY:
                return new CandyFactory();
            case BAR:
                return new BarFactory();
            case WAFFLE:
                return new WaffleFactory();
            default:
                try {
                    throw new IllegalArgumentException("Incompatible sweetness type: " + st.name());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }


}
