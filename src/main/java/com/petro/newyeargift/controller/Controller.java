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
            bounds = Utils.convertInputStringRangeToDoubleArrayAndCheck(inputString);
            if (bounds == null) {
                view.printWrongInput();
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
            res =  Utils.convertInputStringToIntAndCheck(inputString, 2);
            if (res == ERROR_FLAG) {
                view.printWrongInput();
                continue;
            }
            break;
        }
        gift.sort(res);
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

        List<String> sweetnessesTypes = Utils.getAvailableSweetnessTypesFromFolder();

        view.printSweetnesses(sweetnessesTypes);

        view.printMessage(View.PRINT_CHOOSE_SWEETNESS_TYPE);

        int type = inputSweetnessType(scanner, sweetnessesTypes.size());

        ProcessedFileContent fileContent =
                Utils.getFileContent(sweetnessesTypes.get(type - 1));

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
            numberAndAmount = Utils.convertInputStringToNumberAndAmountAndCheck(sweetnessAndAmount, bound);
            if (numberAndAmount == null) {
                view.printWrongInput();
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
            type = Utils.convertInputStringToIntAndCheck(typeString, bound);
            if (type == ERROR_FLAG) {
                view.printWrongInput();
                continue;
            }
            break;
        }
        return type;
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
