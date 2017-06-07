package com.petro.newyeargift;

import com.petro.newyeargift.controller.Controller;
import com.petro.newyeargift.controller.EnumNotFoundException;
import com.petro.newyeargift.gift.Gift;
import com.petro.newyeargift.view.View;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws EnumNotFoundException {
        new Controller(new Gift(), new View()).process();
    }
}
