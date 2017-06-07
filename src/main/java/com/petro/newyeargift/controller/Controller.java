package com.petro.newyeargift.controller;

import com.petro.newyeargift.gift.Gift;
import com.petro.newyeargift.gift.confection.Sweetness;
import com.petro.newyeargift.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Controller {
    public static final String DIRECTORY_PATH = "sweet";
    public static final String RANGE_SPLITTER = "-";
    public static final int ERROR_FLAG = -1;
    private Gift gift;
    private View view;

    public Controller(Gift gift, View view) {
        this.gift = gift;
        this.view = view;
    }

    public void process() throws EnumNotFoundException {

        Scanner scanner = new Scanner(System.in);

        view.printMessage(View.PRINT_GREETING);

        createGift(scanner);
        sortGift(scanner);
        findSweetnessBySugarPercentageRange(scanner);
        scanner.close();

    }

    private void findSweetnessBySugarPercentageRange(Scanner scanner) {
        view.printMessage(View.PRINT_SEARCH);
        String inputString;
        Double[] bounds;

        while (true) {
            inputString = scanner.nextLine().trim();
            bounds = convertInputStringRangeToDoubleArray(inputString);
            if (bounds == null) {
                continue;
            }
            break;
        }
        List<Sweetness> filteredSweetnesses =
                filterSweetnessesBySugarPercentageRange(bounds[0], bounds[1]);

        view.printSearchResultInput(bounds[0], bounds[1]);
        view.printSweetnesses(filteredSweetnesses);
    }

    public List<Sweetness> filterSweetnessesBySugarPercentageRange(Double lowBound, Double highBound) {
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
            view.printWrongInput();
            return null;
        }
        boundsString = inputString.split(RANGE_SPLITTER);
        bounds[0] = Double.parseDouble(boundsString[0]);
        bounds[1] = Double.parseDouble(boundsString[1]);
        if (bounds[0] > bounds[1]) {
            view.printWrongInput();
            return null;
        }
        return bounds;
    }

    public void createGift(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            chooseSweetness(scanner);
            view.printCurrentGiftWeight(gift.weight());
            flag = askForContinue(scanner);
        }
        view.printMessage(gift.toString());
    }

    public void sortGift(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            chooseSortStrategy(scanner);
            view.printMessage(gift.toString());
            flag = askForContinue(scanner);
        }
    }

    public void chooseSortStrategy(Scanner scanner) {
        view.printMessage(View.PRINT_SORT_GIFT_BY);
        String inputString;
        int res;
        while (true) {
            inputString = scanner.nextLine().trim();
            res = convertInputStringToInt(inputString, 2);
            if (res != ERROR_FLAG) {
                break;
            }
        }
        gift.sort(res);
    }

    public int convertInputStringToInt(String inputString, int highBound) {
        int res = 0;
        if (!inputString.matches(Utils.NUMBER_REGEX)) {
            view.printWrongInput();
            return ERROR_FLAG;
        }
        res = Integer.parseInt(inputString);

        if (res == 0 || res > highBound) {
            view.printWrongInput();
            return ERROR_FLAG;
        }
        return res;
    }

    public boolean askForContinue(Scanner scanner) {
        String choice;
        view.printMessage(View.PRINT_ASK_FOR_CONTINUE);
        while (true) {
            choice = scanner.nextLine();
            if (!choice.matches(Utils.YES_NO_REGEX)) {
                view.printWrongInput();
                continue;
            }
            break;
        }
        return choice.equalsIgnoreCase(Utils.CONTINUE_CLAUSE_YES) ? true : false;
    }

    public void chooseSweetness(Scanner scanner) {
        view.printMessage(View.PRINT_AVAILABLE_SWEETNESSES_TYPES);

        List<String> sweetnessesTypes = Utils.getAvailableSweetnessTypesFolder(DIRECTORY_PATH);

        view.printSweetnesses(sweetnessesTypes);

        view.printMessage(View.PRINT_CHOOSE_SWEETNESS_TYPE);

        int type = inputSweetnessType(scanner, sweetnessesTypes.size());

        ProcessedFileContent fileContent =
                Utils.getFileContent(DIRECTORY_PATH, sweetnessesTypes.get(type - 1));

        printProcessedFileContent(fileContent);

        Map<Integer, Sweetness> sweetnesses = fileContent.getSwetnesses();

        view.printMessage(View.PRINT_CHOOSE_SWEETNESSES_AND_AMOUNT);

        int[] numberAndAmount = getNumberAndAmount(scanner, sweetnesses.size());

        Utils.insertManySweetnessesInGift(gift, sweetnesses.get(numberAndAmount[0]),
                numberAndAmount[1]);
    }

    public int[] getNumberAndAmount(Scanner scanner, int bound) {
        String sweetnessAndAmount;
        int[] numberAndAmount;
        while (true) {
            sweetnessAndAmount = scanner.nextLine().trim();
            numberAndAmount = convertInputStringToNumberAndAmount(sweetnessAndAmount, bound);
            if (numberAndAmount == null) {
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



    public int[] convertInputStringToNumberAndAmount(String inputString, int highBound) {
        int[] res = new int[2];
        if (!inputString.matches(Utils.SWEETNESS_AND_AMOUNT_REGEX)) {
            view.printWrongInput();
            return null;
        }
        String[] inputs = inputString.split("-");
        res[0] = Integer.parseInt(inputs[0]);
        res[1] = Integer.parseInt(inputs[1]);

        if (res[0] == 0 || res[0] > highBound) {
            view.printWrongInput();
            return null;
        }
        return res;
    }

    public void printProcessedFileContent(ProcessedFileContent fileContent) {
        Map<Integer, Sweetness> sweetnesses = fileContent.getSwetnesses();
        List<Integer> errorLines = fileContent.getErrorLines();
        view.printReadSweetnessesFromFile(sweetnesses);
        if (errorLines.size() != 0) {
            view.printMessage(View.PRINT_ERRORS_IN_LINE);
            view.printErrorLinesNumbers(errorLines);
        }
    }


}
