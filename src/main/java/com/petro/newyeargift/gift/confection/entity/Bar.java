package com.petro.newyeargift.gift.confection.entity;

import com.petro.newyeargift.gift.confection.Confection;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Bar extends Confection {

        private boolean consistNuts;


    protected Bar(){}

    public boolean isConsistNuts() {
        return consistNuts;
    }

    public class Builder extends Confection.Builder<Builder> {

        protected Builder(){}

        public Builder setFlavor(boolean consistNuts){
            Bar.this.consistNuts = consistNuts;
            return this;
        }

        @Override
        public Bar build() {
            super.build();
            return Bar.this;
        }
    }

    public static Builder getBuilder() {
        return new Bar().new Builder();
    }

    @Override
    public String toString() {
        return "Bar{ " +
                " name = '" + name + '\'' +
                ", glaze = " + glaze +
                " consistNuts = " + consistNuts +
                ", weight = " + weight +
                ", sugarValue = " + sugarValue +
                ", sugarPercentage = " + sugarPercentage +
                ", price = " + price +
                '}';
    }
}
