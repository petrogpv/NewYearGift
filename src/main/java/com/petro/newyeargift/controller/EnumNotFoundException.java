package com.petro.newyeargift.controller;

/**
 * Created by Валерий on 27.05.2017.
 */
public class EnumNotFoundException extends IllegalArgumentException {
    public EnumNotFoundException(String string){
        super("Enum not found for string: " + string);
    }
}
