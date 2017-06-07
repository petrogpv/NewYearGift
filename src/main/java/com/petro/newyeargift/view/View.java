package com.petro.newyeargift.view;

import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.List;
import java.util.Map;

/**
 * Created by Администратор on 26.05.2017.
 */
public class View implements  ViewConstants{
    public void printMessage(String string){
        System.out.println(string);
    }
    public  void printWrongInput(){
        printMessage(WRONG_INPUT);
    }
    public  void printSearchResultInput(Double lowBound, Double highBound){
        printMessage(PRINT_SEARCH_RESULT + lowBound + DASH + highBound);
    }
    public void printCurrentGiftWeight(Double weight){
        printMessage(PRINT_CURRENT_GIFT_WEIGHT + weight);
    }
    public void printSweetnesses(List<?> sweetnessesTypes) {
        for (int i = 0; i < sweetnessesTypes.size(); i++) {
            printMessage("" + (i + 1) + ". " + sweetnessesTypes.get(i));
        }
    }
    public void printReadSweetnessesFromFile(Map<Integer, Sweetness> sweetnesses){
        for (Map.Entry<Integer, Sweetness> entry : sweetnesses.entrySet()) {
            printMessage(entry.getKey() + ". " + entry.getValue());
        }
    }
    public void printErrorLinesNumbers(List<Integer> errorLines){
        errorLines.stream().forEach(x -> printMessage(x + ", "));
        printMessage("");
    }

}

