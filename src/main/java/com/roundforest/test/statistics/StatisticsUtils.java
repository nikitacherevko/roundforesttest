package com.roundforest.test.statistics;

import com.roundforest.test.parser.entity.ReviewEntity;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public final class StatisticsUtils {

    private static final String WORD_SEPARATOR = "[^A-Za-z0-9'-]+";

    private StatisticsUtils() {
    }

    public static List<String> getMostActiveUsers(Set<ReviewEntity> items, int limit) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        Map<String, Integer> users = new HashMap<>();
        items.forEach(review -> {
            String profileName = review.getProfileName();
            Integer count = users.get(profileName);
            users.put(profileName, count == null ? 0 : count + 1);
        });

        return collect(users, limit);
    }

    public static List<String> getMostCommentedItems(Set<ReviewEntity> items, int limit) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        Map<String, Integer> products = new HashMap<>();
        items.forEach(review -> {
            String productId = review.getProductId();
            Integer count = products.get(productId);
            products.put(productId, count == null ? 0 : count + 1);
        });

        return collect(products, limit);
    }

    public static List<String> getMostUsedWords(Set<ReviewEntity> items, int limit) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        Map<String, Integer> wordsCount = new HashMap<>();
        items.forEach(review -> {
            //I'm not sure whether I should only go through comments or summary is also important
            for (String word : review.getText().split(WORD_SEPARATOR)) {
                Integer count = wordsCount.get(word);
                wordsCount.put(word, count == null ? 0 : count + 1);
            }
        });

        return collect(wordsCount, limit);
    }

    private static <K, V extends Comparable<? super V>> List<K> collect(Map<K, V> map, int limit) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(limit)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

}
