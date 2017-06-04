package com.petro.newyeargift.controller;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Валерий on 04.06.2017.
 */
public class UtilsTest {
    @Test
    public void testDoubleRangeRegex(){
        String string = "10.00-20.02";
        assertTrue(string.matches(Utils.DOUBLE_RANGE_REGEX));
    }

}