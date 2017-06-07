package com.petro.newyeargift.view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Валерий on 07.06.2017.
 */
public interface ViewConstants {
    String MESSAGES_BUNDLE_NAME = "messages";
    ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, new Locale("en"));
    String DASH = " - ";
    String WRONG_INPUT = resourceBundle.getString("print.wrong");
    String PRINT_GREETING = resourceBundle.getString("print.greeting");
    String PRINT_SEARCH = resourceBundle.getString("print.search");
    String PRINT_SEARCH_RESULT = resourceBundle.getString("print.search_result");
    String PRINT_CURRENT_GIFT_WEIGHT = resourceBundle.getString("print.current_gift_weight");
    String PRINT_SORT_GIFT_BY = resourceBundle.getString("print.sort_gift_by");
    String PRINT_ASK_FOR_CONTINUE = resourceBundle.getString("print.continue");
    String PRINT_AVAILABLE_SWEETNESSES_TYPES = resourceBundle.getString("print.available_sweetnesses_types");
    String PRINT_CHOOSE_SWEETNESS_TYPE = resourceBundle.getString("print.choose_sweetness_type");
    String PRINT_CHOOSE_SWEETNESSES_AND_AMOUNT = resourceBundle.getString("print.choose_sweetnesses_and_amount");
    String PRINT_ERRORS_IN_LINE = resourceBundle.getString("print.errors_in_file");
}
