package com.petro.newyeargift.controller;

/**
 * Created by Валерий on 27.05.2017.
 */
public class EnumNotFoundException extends Exception {
    public EnumNotFoundException(String string, Throwable ex){
        super("Enum not found for string: " + string, ex);
    }
}
