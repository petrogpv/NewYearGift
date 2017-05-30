package com.petro.newyeargift.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Администратор on 27.05.2017.
 */
public class Utils {

    public static final int PRICE_ROUND_PRECISION = 2;
    public static final String DECIMAL_ROUND_FORMAT = "0.00";
    public static final String SPLIT_REGEX = "\\s+";
    public static final String DIRECTORY_PATH = "sweet";

    public static String [] parseLine(String line){
        return line.split(SPLIT_REGEX);
    }
    public static Double roundDouble(Double d){
        DecimalFormat df = new DecimalFormat(DECIMAL_ROUND_FORMAT);
        String format = df.format(d);
        Double res = null;
        try {
            res = (Double)df.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static BigDecimal roundBigDecimal(BigDecimal price){
        return price.setScale(PRICE_ROUND_PRECISION,RoundingMode.CEILING);
    }

    public static <E extends Enum<E>> Enum<E> convertStringIntoEnum(Class<E> enumClass, String string)
                                                throws  EnumNotFoundException   {
       return Enum.valueOf(enumClass,string);

//        Enum<E> result = null;
//        for (Enum<E> enumVal:enumClass.getEnumConstants() ) {
//            if (enumVal.name().equalsIgnoreCase(string)){
//                result = enumVal;
//                return result;
//            }
//        }
//        throw new EnumNotFoundException(string);
    }
    public static Double convertStringIntoDouble(String string){
        return roundDouble(Double.parseDouble(string));
    }
    public static BigDecimal convertStringIntoBigDecimal(String string){
        return roundBigDecimal(new BigDecimal(string));
    }

    public  List<String> readFromFile (){
        ClassLoader classLoader = getClass().getClassLoader();
        File directory = new File(classLoader.getResource(DIRECTORY_PATH).getPath());
        File [] files = directory.listFiles();


        Map<String,Map<Integer,String>> map = new HashMap<>();


        String line;
        int i = 1;

        for (File f : files ) {
            Map<Integer,String> internalMap = new HashMap<>();
            i = 1;
            try {
                try (
                        BufferedReader br = new BufferedReader(new FileReader(f))
                ) {
                    while ((line = br.readLine()) != null) {
                        internalMap.put(i, line);
                        i++;
                    }
                }
                map.put(f.getName(), internalMap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return null;
    }

}
