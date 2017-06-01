package com.petro.newyeargift.gift;


import com.petro.newyeargift.controller.sort.SortStrategy;
import com.petro.newyeargift.controller.sort.SortSweetnesses;
import com.petro.newyeargift.controller.sort.SortSweetnessesByWeight;
import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Gift{

    private List<Sweetness> sweetnesses;


    public Gift() {
       sweetnesses = new ArrayList<>();
    }

    public void insertSweetness(Sweetness sweet){
        sweetnesses.add(sweet);
    }

    public List<Sweetness> getSweetnesses(){
        return Collections.unmodifiableList(sweetnesses);
    }

    public void sort(int sortType){
        SortStrategy.sort(sortType, sweetnesses);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("Gift:\n");
        sweetnesses.stream().forEach(x -> stringBuilder
                .append(x.toString())
                .append("\n"));

        return stringBuilder.toString();
    }
}
