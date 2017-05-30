package com.petro.newyeargift;

import com.petro.newyeargift.controller.Controller;
import com.petro.newyeargift.controller.EnumNotFoundException;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws EnumNotFoundException {
        new Controller().process();
    }
}
