package com.petro.newyeargift.controller;

import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.*;

/**
 * Created by Валерий on 01.06.2017.
 */
public class ProcessedFileContent {
    private List<Integer> errorLines = new ArrayList<>();
    private int index = 1;
    private Map<Integer, Sweetness> sweetnessFromFile = new HashMap<>();

    public List<Integer> getErrorLines() {
        return Collections.unmodifiableList(errorLines);
    }

    public void addErrorLine(int errorLine) {
        this.errorLines.add(errorLine);
    }

    public void addSweetness (Sweetness sweetness){
        sweetnessFromFile.put(index, sweetness);
        index++;
    }

    public Map<Integer, Sweetness> getSwetnesses(){
        return Collections.unmodifiableMap(sweetnessFromFile);
    }
}
