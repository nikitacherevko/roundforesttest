package com.roundforest.test.translator;


import com.roundforest.test.parser.entity.ReviewEntity;
import com.roundforest.test.translator.utils.TranslationStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GoogleTranslationService {

    private static final int THREADS_LIMIT = 100;
    private static final ExecutorService MAIN_EXECUTOR =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    public void translateReviews(Set<ReviewEntity> reviews) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_LIMIT);
        Set<Set<ReviewEntity>> reviewsToTranslate = new HashSet<>();
        Set<ReviewEntity> subSet = new HashSet<>();
        int currentStringSize = 0;
        for (ReviewEntity entity : reviews) {
            String text = entity.getText();
            if (StringUtils.isBlank(text)) {
                continue;
            }

            if (text.length() >= TranslationStringUtils.CHARACTERS_LIMIT) {
                reviewsToTranslate.add(Collections.singleton(entity));
            } else if (text.length() + currentStringSize < TranslationStringUtils.CHARACTERS_LIMIT) {
                subSet.add(entity);
                currentStringSize += text.length();
            } else {
                reviewsToTranslate.add(subSet);
                subSet = new HashSet<>();
                currentStringSize = 0;
            }
        }
        reviewsToTranslate.add(subSet);

        reviewsToTranslate.forEach(reviewItems ->
                MAIN_EXECUTOR.submit(new ReviewTranslationProcessor(reviewItems, executorService)));
        MAIN_EXECUTOR.shutdown();
    }

}
