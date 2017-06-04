package com.petro.newyeargift.controller;

import com.petro.newyeargift.controller.factories.FactorySelector;
import com.petro.newyeargift.controller.factories.SweetnessFactory;
import com.petro.newyeargift.gift.Gift;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Администратор on 27.05.2017.
 */
public class Utils {

    public static final int PRICE_ROUND_PRECISION = 2;
    public static final String DECIMAL_ROUND_FORMAT = "0.00";

    public static final String BOOLEAN_TRUE_STRING = "yes";
    public static final String BOOLEAN_FALSE_STRING = "no";
    public static final String CANDY_REGEX = "([A-Za-z]+\\s?-?[A-Za-z]+)\\s+([A-Za-z]+)\\s+([A-Za-z]+)\\s+" +
            "(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s*";
    public static final String SWEETNESS_AND_AMOUNT_REGEX = "(\\d+)-(\\d+)";
    public static final String NUMBER_REGEX = "\\d";
    public static final String YES_NO_REGEX = "[Yy]|[Nn]";
    public static final String DOUBLE_RANGE_REGEX = "\\d{1,2}(\\.\\d{1,2})?-\\d{1,2}(\\.\\d{1,2})?";

    public static Double roundDouble(Double d) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(DECIMAL_ROUND_FORMAT);
        String format = df.format(d);
        Double res = null;
        res = Double.parseDouble(format);
        return res;
    }
    public static Double convertStringIntoDouble(String string) {
        return roundDouble(Double.parseDouble(string));
    }

    public static BigDecimal convertStringIntoBigDecimal(String string) {
        return roundBigDecimal(new BigDecimal(string));
    }
    public static BigDecimal roundBigDecimal(BigDecimal price) {
        return price.setScale(PRICE_ROUND_PRECISION, RoundingMode.CEILING);
    }

    public static <E extends Enum<E>> E convertStringIntoEnum(Class<E> enumClass, String string)
            throws EnumNotFoundException {
        try {
            E enumValue = Enum.valueOf(enumClass, string);
            return enumValue;
        } catch (IllegalArgumentException e) {
            throw new EnumNotFoundException(string, e);
        }

    }

    public static boolean convertStringYesNoToBoolean(String string) {
        if (string.equalsIgnoreCase(BOOLEAN_TRUE_STRING)) {
            return true;
        } else if (string.equalsIgnoreCase(BOOLEAN_FALSE_STRING)) {
            return false;
        }
        throw new IllegalArgumentException("Wrong input: " + string);
    }

    public static Sweetness createSweetnessFromFileLine(String fileName, String line) throws EnumNotFoundException {
        FactorySelector factorySelector = FactorySelector.getInstance();
        SweetnessType sweetnessType = SweetnessType.valueOf(fileName);
        SweetnessFactory sweetnessFactory = factorySelector.getSweetnessFactory(sweetnessType);

        Pattern pattern = Pattern.compile(CANDY_REGEX);
        Matcher matcher = pattern.matcher(line);
        List<String> matchedStrings = new ArrayList<>();
        int groupsCount = 0;
        if (matcher.matches()) {
            groupsCount = matcher.groupCount();
            for (int i = 1; i <= groupsCount; i++) {
                matchedStrings.add(matcher.group(i));
            }

            return sweetnessFactory.create(matchedStrings.toArray(new String[matchedStrings.size()]));
        } else {
            return null;
        }

    }

    public static String getFileNameWithoutExtension(File file) {
        String filename = file.getName();
        int indexOfDot = filename.lastIndexOf(".");
        return filename.substring(0, indexOfDot);
    }

    public static List<String> getAvailableSweetnessTypesFolder(String directoryPath) {
        File[] files = readFilesFromFolder(directoryPath);
        List<String> filenames = new ArrayList<>();

        for (File file : files) {
            filenames.add(getFileNameWithoutExtension(file));
        }
        return filenames;
    }

    public  static ProcessedFileContent getFileContent (String directoryPath, String sweetnessType){
        List<Sweetness> sweetnesses = getSweetnessesFromFile(directoryPath, sweetnessType);
        ProcessedFileContent fileContent = new ProcessedFileContent();
        Sweetness sweetness = null;
        for (int i = 0; i < sweetnesses.size() ; i++) {
            sweetness = sweetnesses.get(i);
            if(sweetness != null){
                fileContent.addSweetness(sweetness);
            }else{
                fileContent.addErrorLine(i + 1);

            }
        }
        return fileContent;
    }
    public static List<Sweetness> getSweetnessesFromFile(String directoryPath, String sweetnessType) {
        File[] files = readFilesFromFolder(directoryPath);
        File file = getFileBySweetnessType(sweetnessType, files);
        List<Sweetness> sweetnesses = new ArrayList<>();
        String line ;
        Sweetness sweetness;
        int i = 0;
        try {
            try (
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ) {
                try {
                    while ((line = br.readLine()) != null) {
                        if (i == 0) {
                            i++;
                        } else {
                            sweetness = createSweetnessFromFileLine(sweetnessType, line);
                            sweetnesses.add(sweetness);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (EnumNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return sweetnesses;
    }

    public static File getFileBySweetnessType(String sweetnessType, File[] files) {
        File file = null;
        for (File f : files) {
            if (f.getName().startsWith(sweetnessType)) {
                file = f;
            }
        }
        return file;
    }

    public static File[] readFilesFromFolder(String directoryPath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File directory = new File(classLoader.getResource(directoryPath).getPath());
        return directory.listFiles();
    }

    public static void insertManySweetnessesInGift(Gift gift, Sweetness sweetness, int amount){
        for (int i = 0; i < amount; i++) {
            gift.insertSweetness(sweetness);
        }
    }

}
