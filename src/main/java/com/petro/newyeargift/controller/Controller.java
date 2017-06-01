package com.petro.newyeargift.controller;

import com.petro.newyeargift.gift.confection.Sweetness;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Администратор on 26.05.2017.
 */
public class Controller {
    public static final String DIRECTORY_PATH = "sweet";

    public void process() throws EnumNotFoundException {

        Scanner scanner = new Scanner(System.in);
        List<String> sweetnessesTypes = Utils.getAvailableSweetnessTypesFolder(DIRECTORY_PATH);

        System.out.println("New Year gift creation! \nAvailable sweetnesses: ");

        for (int i = 0; i < sweetnessesTypes.size(); i++) {
            System.out.println("" + (i + 1) + ". " + sweetnessesTypes.get(i));
        }
        System.out.println("Choose sweetness type to add to gift( \"1\" etc.): ");

        int type = 0;
        while (true) {
            type = scanner.nextInt();

            if (type < 1 || type > sweetnessesTypes.size()) {
                System.out.println("Wrong input: \"" + type + "\". Try again: ");
                continue;
            }
            break;
        }


        List<Sweetness> sweetnesses = Utils.getSweetnessesFromFile(DIRECTORY_PATH, sweetnessesTypes.get(type - 1));

        sweetnesses.stream().forEach(System.out::println);


//        Gift gift = Utils.readFromFiles(DIRECTORY_PATH);
//        System.out.println(gift);
//        gift.sort(SortStrategy.SORT_BY_WEIGHT);
//        System.out.println(gift);

    }


}
