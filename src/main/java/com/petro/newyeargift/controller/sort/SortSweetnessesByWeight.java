package com.petro.newyeargift.controller.sort;

import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Администратор on 01.06.2017.
 */
public class SortSweetnessesByWeight implements SortSweetnesses {

    @Override
    public void sort(List<Sweetness> list) {
        Collections.sort(list, new Comparator<Sweetness>() {
            @Override
            public int compare(Sweetness o1, Sweetness o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }
}
