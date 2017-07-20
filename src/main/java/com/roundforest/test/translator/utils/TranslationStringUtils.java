package com.roundforest.test.translator.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TranslationStringUtils {
    public static final String URL = "https://api.google.com/translate";
    public static final int CHARACTERS_LIMIT = 1000;

    private static final String SENTENCE_DELIMITER = ".";
    private static final char DELIMITER = 170;

    public static String collectText(List<String> reviews) {
        return StringUtils.join(reviews, DELIMITER);
    }

    public static String[] parseReviews(String concatenated) {
        if (Objects.isNull(concatenated)) {
            return new String[0];
        }

        return concatenated.split(String.valueOf(DELIMITER));
    }

    public static List<String> splitString(String string) {
        StringBuilder sb = new StringBuilder(string);
        List<String> substrings = new ArrayList<>();

        while (sb.length() != 0) {
            if (sb.length() <= CHARACTERS_LIMIT) {
                substrings.add(sb.substring(0, sb.length() - 1));
                sb.delete(0, sb.length() - 1);
            } else {
                String substring = sb.substring(0, CHARACTERS_LIMIT - 1);
                int end = substring.lastIndexOf(SENTENCE_DELIMITER);

                if (end == -1) {
                    break;
                } else {
                    substrings.add(sb.substring(0, end));
                    sb.delete(0, end);
                }
            }
        }

        if (sb.length() > TranslationStringUtils.CHARACTERS_LIMIT) {
            //todo: string exceeded limit without dots which signalized about end of sentence
        }

        return substrings;
    }
}
