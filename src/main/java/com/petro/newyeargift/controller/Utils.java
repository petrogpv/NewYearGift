package com.petro.newyeargift.controller;

import com.petro.newyeargift.controller.factories.FactorySelector;
import com.petro.newyeargift.controller.factories.SweetnessFactory;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.gift.confection.SweetnessType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Администратор on 27.05.2017.
 */
public class Utils {

    public static final int PRICE_ROUND_PRECISION = 2;
    public static final String DECIMAL_ROUND_FORMAT = "0.00";
    public static final String SPLIT_REGEX = "\\s+";
    public static final String DIRECTORY_PATH = "sweet";
    public static final String BOOLEAN_TRUE_STRING = "yes";
    public static final String BOOLEAN_FALSE_STRING = "no";
    public static final String CANDY_REGEX = "([A-Za-z]+\\s?-?[A-Za-z]+)\\s+([A-Za-z]+)\\s+([A-Za-z]+)\\s+" +
            "(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s*";

//    public static String [] parseLine(String[] line){
//        return line.split(SPLIT_REGEX);
//    }
    public static Double roundDouble(Double d){
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern(DECIMAL_ROUND_FORMAT);
        String format = df.format(d);
        Double res = null;
            res = Double.parseDouble(format);
        return res;
    }
    public static BigDecimal roundBigDecimal(BigDecimal price){
        return price.setScale(PRICE_ROUND_PRECISION,RoundingMode.CEILING);
    }

    public static <E extends Enum<E>> E convertStringIntoEnum(Class<E> enumClass, String string)
                                                throws  EnumNotFoundException   {
        try {
            E enumValue = Enum.valueOf(enumClass, string);
            return enumValue;
        }catch (IllegalArgumentException e){
            throw new EnumNotFoundException(string, e);
        }

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


        Map<String,Map<Integer,Sweetness>> map = new HashMap<>();


        String line = null;
        String  currentFileName = null;
        Sweetness sweetness = null;
        int i = 1;

        for (File f : files ) {
            currentFileName = getFileNameWithoutExtension(f.getName());
            Map<Integer,Sweetness> internalMap = new HashMap<>();
            i = 0;
            try {
                try (
                        BufferedReader br = new BufferedReader(new FileReader(f))
                ) {
                    while ((line = br.readLine()) != null) {
                        if(i != 0){
                            sweetness = createSweetnessFromFileLine(currentFileName, line);
                            internalMap.put(i, sweetness);
                        }
                        i++;
                    }
                } catch (EnumNotFoundException e) {
                    e.printStackTrace();
                }
                map.put(f.getName(), internalMap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        for (Map.Entry<String, Map<Integer, Sweetness>> m: map.entrySet()) {
            Map<Integer, Sweetness> insideMap = m.getValue();
            for (Map.Entry<Integer, Sweetness> e: insideMap.entrySet()) {
                System.out.println(e.getValue());

            }

        }

        return null;
    }
    public static boolean convertStringYesNoToBoolean(String string){
        if(string.equalsIgnoreCase(BOOLEAN_TRUE_STRING)){
            return true;
        }else if(string.equalsIgnoreCase(BOOLEAN_FALSE_STRING)){
            return false;
        }
        throw new IllegalArgumentException("Wrong input: " + string);
    }

    public static Sweetness createSweetnessFromFileLine (String fileName, String line ) throws EnumNotFoundException {
        FactorySelector factorySelector = FactorySelector.getInstance();
        SweetnessType sweetnessType = SweetnessType.valueOf(fileName);
        SweetnessFactory sweetnessFactory = factorySelector.getSweetnessFactory(sweetnessType);

        Pattern pattern = Pattern.compile(CANDY_REGEX);
        Matcher matcher = pattern.matcher(line);
        List<String> matchedStrings = new ArrayList<>();
        int groupsCount = 0;
        if(matcher.matches()){
            groupsCount = matcher.groupCount();
            for (int i = 1; i <= groupsCount; i++) {
                matchedStrings.add(matcher.group(i));
            }

            return  sweetnessFactory.create(matchedStrings.toArray(new String[matchedStrings.size()]));
        }
        else{
            return null;
        }

    }
    public static String getFileNameWithoutExtension(String filename){
        int indexOfDot = filename.lastIndexOf(".");
        return filename.substring(0, indexOfDot);
    }
}
