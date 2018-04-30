package com.udacity.sandwichclub.utils;

/**
 * Created by sunand on 4/26/18.
 */

public class StringUtils {

    private static final int maxLength = 35;

    public static String formatMultiline(String str) {

        if (str == null  || str.length() == 0) {
            return "";
        }

        if (maxLength < 1) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str);
        int position = sb.length() - 1;
        while (position >=  maxLength - 1) {
            position = str.indexOf(' ', position - maxLength + 1);
            sb.setCharAt(position, '\n');
        }
        return sb.toString();
    }
}
