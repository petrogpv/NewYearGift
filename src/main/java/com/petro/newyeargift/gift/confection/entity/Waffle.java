package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;
import com.petro.newyeargift.gift.confection.components.Flavour;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Waffle extends Confection {

    private Flavour flavour;

    protected Waffle(){}

    public Flavour getFlavour() {
        return flavour;
    }
    public class Builder extends Confection.Builder<Builder> {

        protected Builder(){}

        public Builder setFlavor(Flavour flavour){
            Waffle.this.flavour = flavour;
            return this;
        }

        @Override
        public Waffle build() {
            super.build();
            return Waffle.this;
        }
    }

    public static Builder getBuilder() {
        return new Waffle().new Builder();
    }

    @Override
    public String toString() {
        return "Waffle{ " +
                " name = '" + name + '\'' +
                ", glaze = " + glaze +
                " flavour   = " + flavour +
                ", weight = " + weight +
                ", sugarValue = " + sugarValue +
                ", sugarPercentage = " + sugarPercentage +
                ", price = " + price +
                '}';
    }
}
