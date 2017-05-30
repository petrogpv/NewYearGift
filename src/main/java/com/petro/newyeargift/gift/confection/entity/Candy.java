package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;
import com.petro.newyeargift.gift.confection.components.Filling;

/**
 * Created by Администратор on 25.05.2017.
 */
public class Candy extends Confection {

    private Filling filling;

    public Filling getFilling() {
        return filling;
    }
    protected Candy(){}
    public class Builder extends Confection.Builder<Builder> {

        protected Builder(){}

        public Builder setFilling(Filling filling){
            Candy.this.filling = filling;
            return this;
        }

        @Override
        public Candy build() {
            super.build();
            return Candy.this;
        }
    }

    public static Builder getBuilder() {
        return new Candy().new Builder();
    }

    @Override
    public String toString() {
        return "Candy{ " +
                " name = '" + name + '\'' +
                ", glaze = " + glaze +
                " filling = " + filling +
                ", weight = " + weight +
                ", sugarValue = " + sugarValue +
                ", sugarPercentage = " + sugarPercentage +
                ", price = " + price +
                '}';
    }
}
