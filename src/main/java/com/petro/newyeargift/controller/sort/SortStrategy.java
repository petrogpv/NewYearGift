package com.petro.newyeargift.controller.sort;

import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.List;

/**
 * Created by Администратор on 01.06.2017.
 */
public class SortStrategy {
    public static final int SORT_BY_WEIGHT = 1;
    public static final int SORT_BY_SUGAR_PERCENTAGE= 2;
    private static SortSweetnesses sortSweetnesses;

    public static void sort(int sortType, List<Sweetness> list){
        switch(sortType) {
            case SORT_BY_WEIGHT:
                sortSweetnesses = new SortSweetnessesByWeight();
                break;
            case SORT_BY_SUGAR_PERCENTAGE:
                sortSweetnesses = new SortSweetnessesBySugarPercentage();
                break;
            default:
                throw new IllegalArgumentException("Wrong sort strategy number");
        }
        sortSweetnesses.sort(list);
    }

}
