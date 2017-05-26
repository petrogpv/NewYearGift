package com.petro.newyeargift.gift;


import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Gift {
    private List<Sweetness> sweetnesses;

    public Gift() {
       sweetnesses = new ArrayList<>();
    }

    public void insertSweetness(Sweetness sweet){
        sweetnesses.add(sweet);
    }

    public List<Sweetness> getSweetnesses(){
        return sweetnesses;
    }
}
