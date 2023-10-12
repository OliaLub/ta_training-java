package com.epam.training.olha_haichenkova.task_3.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    public static String isolateNumberFromString(String stringWithNumber) {
        String[] splitMessage = stringWithNumber.split("\\s");
        String number = null;
        for (String element : splitMessage) {
            if (element.matches("[0-9,.]+")) {
                number = element;
                break;
            }
        }
        if (number == null) {
            throw new IllegalArgumentException("There is no number in string");
        }
        return number;
    }

    public static String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}
