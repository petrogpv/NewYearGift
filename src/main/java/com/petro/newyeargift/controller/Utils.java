package com.petro.newyeargift.controller;

/**
 * Created by Администратор on 27.05.2017.
 */
public class Utils {

    public static final String SPLIT_REGEX = "\\s+";

    public String [] parseLine(String line){
        return line.split(SPLIT_REGEX);
    }
}
