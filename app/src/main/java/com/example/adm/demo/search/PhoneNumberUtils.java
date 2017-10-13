package com.example.adm.demo.search;

/**
 * Created by Adm on 2017/10/13.
 */


public class PhoneNumberUtils {
    public static String process(String number) {
        if (number == null) {
            return null;
        }

        if (number.contains("-")) {
            number = number.replaceAll("-", "");
        }

        if (number.contains(" ")) {
            number = number.replaceAll(" ", "");
        }

        if (number.contains("+86")) {
            number = number.replaceAll("\\+86", "");
        }

        return number;
    }
}
