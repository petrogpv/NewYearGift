package com.petro.newyeargift.controller;

import com.petro.newyeargift.gift.Gift;
import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Controller {
    public static final String DIRECTORY_PATH = "sweet";
    private Gift gift = new Gift();

    public void process() throws EnumNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("New Year gift creation! ");

        createGift(scanner);
        sortGift(scanner);
        findSweetnessBySugarPercentageRange(scanner);
        scanner.close();

    }

    private void findSweetnessBySugarPercentageRange(Scanner scanner) {
        System.out.println("Search sweetness by sugar percentage range (\"10.25-20.5\" etc.): ");
        String inputString;
        Double[] bounds;

        while (true) {
            inputString = scanner.nextLine().trim();
            bounds = convertInputStringRangeToDoubleArray(inputString);
            if(bounds == null){
                continue;
            }
            break;
        }
        List<Sweetness> filteredSweetnesses =
                filterSweetnessesBySugarPercentageRange(bounds[0], bounds[1]);

        System.out.println("Result for " + bounds[0] + " - " + bounds[1]);
        filteredSweetnesses.stream().forEach(System.out::println);
    }

    public List<Sweetness> filterSweetnessesBySugarPercentageRange(Double lowBound, Double highBound){
        List<Sweetness> filteredSweetnesses = new ArrayList<>();
        List<Sweetness> sweetnesses = gift.getSweetnesses();
        double sugarPercentage = 0.0;
        for (Sweetness sweetness : sweetnesses) {
            sugarPercentage = sweetness.getWeight();
            if (sugarPercentage >= lowBound && sugarPercentage <= highBound) {
                filteredSweetnesses.add(sweetness);
            }
        }
        return filteredSweetnesses;
    }

    public Double[] convertInputStringRangeToDoubleArray(String inputString) {
        String[] boundsString;
        Double[] bounds = new Double[2];
        if (!inputString.matches(Utils.DOUBLE_RANGE_REGEX)) {
            System.out.println("Wrong input! Try again: ");
            return null;
        }
        boundsString = inputString.split("-");
        bounds[0] = Double.parseDouble(boundsString[0]);
        bounds[1] = Double.parseDouble(boundsString[1]);
        if (bounds[0] > bounds[1]) {
            System.out.println("Wrong input! Try again: ");
            return null;
        }
        return bounds;
    }

    public void createGift(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            chooseSweetness(scanner);
            System.out.println("Current giftWeight: " + gift.weight());
            flag = askForContinue(scanner);
        }
        System.out.println(gift);
    }

    public void sortGift(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            chooseSortStrategy(scanner);
            System.out.println(gift);
            flag = askForContinue(scanner);
        }
    }

    public void chooseSortStrategy(Scanner scanner) {
        System.out.println("Sort gift by:");
        System.out.println("Weight - \"1\", ");
        System.out.println("Sugar percentage - \"2\":");
        String inputString;
        int res;
        while (true) {
            inputString = scanner.nextLine().trim();
            res = convertInputStringToInt(inputString, 2);
            if (res > 0) {
                break;
            }
        }
        gift.sort(res);
    }

    public int convertInputStringToInt(String inputString, int highBound) {
        int res = 0;
        if (!inputString.matches(Utils.NUMBER_REGEX)) {
            System.out.println("Wrong input! Try again: ");
            return -1;
        }
        res = Integer.parseInt(inputString);

        if (res == 0 || res > highBound) {
            System.out.println("Wrong input! Try again: ");
            return -1;
        }
        return res;
    }

    public boolean askForContinue(Scanner scanner) {
        String choice = null;
        System.out.println("Continue? \"y\" - yes, \"n\" - no: ");
        while (true) {
            choice = scanner.nextLine();
            if (!choice.matches(Utils.YES_NO_REGEX)) {
                System.out.println("Wrong input! Try again: ");
                continue;
            }
            break;
        }
        return choice.equalsIgnoreCase("y") ? true : false;
    }

    public void chooseSweetness(Scanner scanner) {
        System.out.println("Available sweetnesses types: ");

        List<String> sweetnessesTypes = Utils.getAvailableSweetnessTypesFolder(DIRECTORY_PATH);

        printSweetnesses(sweetnessesTypes);

        System.out.println("Choose sweetness type to add to gift( \"1\" etc.): ");

        int type = inputSweetnessType(scanner, sweetnessesTypes.size());

        ProcessedFileContent fileContent =
                Utils.getFileContent(DIRECTORY_PATH, sweetnessesTypes.get(type - 1));

        printProcessedFileContent(fileContent);

        Map<Integer, Sweetness> sweetnesses = fileContent.getSwetnesses();

        System.out.println("Choose sweetness and amount (\"1-3\" etc.): ");

        String sweetnessAndAmount;

        int [] numberAndAmount = getNumberAndAmount(scanner, sweetnesses.size());

        Utils.insertManySweetnessesInGift(gift, sweetnesses.get(numberAndAmount[0]),
                numberAndAmount[1]);
    }

    public int[] getNumberAndAmount(Scanner scanner, int bound) {
        String sweetnessAndAmount;
        int[] numberAndAmount;
        while (true) {
            sweetnessAndAmount = scanner.nextLine().trim();
            numberAndAmount = convertInputStringToNumberAndAmount(sweetnessAndAmount, bound);
            if(numberAndAmount == null){
                continue;
            }
            break;
        }
        return numberAndAmount;
    }

    public int inputSweetnessType(Scanner scanner, int bound) {
        int type;
        String typeString;
        while (true) {
            typeString = scanner.nextLine().trim();
            type = convertInputStringToInt(typeString, bound);
            if (type > 0) {
                break;
            }
        }
        return type;
    }

    public void printSweetnesses(List<String> sweetnessesTypes) {
        for (int i = 0; i < sweetnessesTypes.size(); i++) {
            System.out.println("" + (i + 1) + ". " + sweetnessesTypes.get(i));
        }
    }

    public int [] convertInputStringToNumberAndAmount(String inputString, int highBound) {
        int [] res = new int[2];
        if (!inputString.matches(Utils.SWEETNESS_AND_AMOUNT_REGEX)) {
            System.out.println("Wrong input! Try again: ");
            return null;
        }
        String[] inputs = inputString.split("-");
        res[0] = Integer.parseInt(inputs[0]);
        res[1] = Integer.parseInt(inputs[1]);

        if (res[0] == 0 || res[0] > highBound) {
            System.out.println("Wrong input! Try again: ");
            return null;
        }
        return res;
    }

    public void printProcessedFileContent(ProcessedFileContent fileContent) {
        Map<Integer, Sweetness> sweetnesses = fileContent.getSwetnesses();
        List<Integer> errorLines = fileContent.getErrorLines();
        for (Map.Entry<Integer, Sweetness> entry : sweetnesses.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        if (errorLines.size() != 0) {
            System.out.print("There are errors in file. Can not read lines: ");
            errorLines.stream().forEach(x -> System.out.print(x + ", "));
            System.out.println("");
        }
    }


}
