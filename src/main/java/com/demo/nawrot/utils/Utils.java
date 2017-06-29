package com.demo.nawrot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class Utils {
    public static final Logger LOGGER = LoggerFactory.getLogger("LOGGER");

    private Utils() {
    }

    public static int parseIntFromString(String stringToParse) {
        int valueFromString = 0;
        try {
            valueFromString = Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            LOGGER.debug("Cannot parse integer from string", e);
        }
        return valueFromString;
    }

    public static String getTextWithoutWhiteSpaces(String textWithPrice) {
        return textWithPrice.replace(" ", "");
    }
}
