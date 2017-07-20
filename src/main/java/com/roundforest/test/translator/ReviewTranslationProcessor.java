package com.roundforest.test.translator;


import com.roundforest.test.parser.entity.ReviewEntity;
import com.roundforest.test.translator.domain.RequestItem;
import com.roundforest.test.translator.utils.TranslationStringUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ReviewTranslationProcessor extends Thread {

    private Set<ReviewEntity> reviews;
    private ExecutorService executorService;

    ReviewTranslationProcessor(Set<ReviewEntity> reviews, ExecutorService executorService) {
        this.reviews = reviews;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            if (reviews.size() == 1) {
                ReviewEntity review = reviews.stream().findFirst().get();
                String reviewText = review.getText();
                if (reviewText.length() > TranslationStringUtils.CHARACTERS_LIMIT) {
                    processStringLimitExceeded(review);
                } else {
                    processSingleString(review);
                }
            } else {
                processMultipleReviews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processSingleString(ReviewEntity review) throws InterruptedException, ExecutionException {
        RequestItem request =
                new RequestItem(Locale.ENGLISH.getLanguage(), Locale.FRENCH.getLanguage(), review.getText());

        String result = executorService.submit(new ReviewTranslationProvider(request)).get();
        review.setText(result);
    }

    private void processStringLimitExceeded(ReviewEntity review) throws InterruptedException, ExecutionException {
        List<String> substrings = TranslationStringUtils.splitString(review.getText());
        List<String> result = new CopyOnWriteArrayList<>();
        final CountDownLatch countDownLatch = new CountDownLatch(substrings.size());

        for (int i = 0; i < substrings.size(); i++) {
            new Thread(new ComplexWordProcessor(substrings.get(i), countDownLatch, result, i)).start();
        }

        review.setText(result.stream().collect(Collectors.joining()));
    }

    private void processMultipleReviews() throws InterruptedException, ExecutionException {
        HashSet<ReviewEntity> linkedReviews = new LinkedHashSet<>(reviews);
        List<String> textReviews = new ArrayList<>(reviews.size());
        for (ReviewEntity entity : linkedReviews) {
            textReviews.add(entity.getText());
        }

        String stringReviews = TranslationStringUtils.collectText(textReviews);
        RequestItem request = new RequestItem(Locale.ENGLISH.getLanguage(), Locale.FRENCH.getLanguage(), stringReviews);

        String result = executorService.submit(new ReviewTranslationProvider(request)).get();
        String[] parsedResult = TranslationStringUtils.parseReviews(result);

        int currentIndex = 0;
        for (ReviewEntity entity : linkedReviews) {
            entity.setText(parsedResult[currentIndex]);
            currentIndex++;
        }
    }

    private class ComplexWordProcessor implements Runnable {

        private String substring;
        private CountDownLatch countDownLatch;
        private List<String> textReviews;
        private int index;

        ComplexWordProcessor(String substring, CountDownLatch countDownLatch, List<String> textReviews,
                                    int index) {
            this.substring = substring;
            this.countDownLatch = countDownLatch;
            this.textReviews = textReviews;
            this.index = index;
        }

        @Override
        public void run() {
            RequestItem request = new RequestItem(Locale.ENGLISH.getLanguage(), Locale.FRENCH.getLanguage(), substring);
            Future<String> substringFuture = executorService.submit(new ReviewTranslationProvider(request));

            try {
                String result = substringFuture.get();
                textReviews.add(index, result);
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
