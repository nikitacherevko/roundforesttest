package com.roundforest.test;

import com.roundforest.test.parser.CsvParser;
import com.roundforest.test.parser.entity.ReviewEntity;
import com.roundforest.test.parser.impl.CsvParserImpl;
import com.roundforest.test.statistics.StatisticsUtils;
import com.roundforest.test.translator.GoogleTranslationService;
import org.apache.commons.lang3.BooleanUtils;

import java.io.File;
import java.util.List;
import java.util.Set;


public class TestApplication {

	public static void main(String[] args) {
		String filePath = System.getProperty("path");
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("Submitted path of CSV file is incorrect");
			return;
		}

		CsvParser csvParser = new CsvParserImpl();
		Set<ReviewEntity> parsedFile = csvParser.parseFile(file);
		System.out.println(parsedFile.size());

		List<String> mostActiveUsers = StatisticsUtils.getMostActiveUsers(parsedFile, 1000);
		System.out.println("Most active users are:" + System.lineSeparator());
		mostActiveUsers.forEach(username -> System.out.println(username + System.lineSeparator()));

		List<String> mostCommentedProducts = StatisticsUtils.getMostCommentedItems(parsedFile, 1000);
		System.out.println("Most commented food items are:" + System.lineSeparator());
		mostCommentedProducts.forEach(product -> System.out.println(product + System.lineSeparator()));

		List<String> mostUsedWords = StatisticsUtils.getMostUsedWords(parsedFile, 1000);
		System.out.println("Most used words are:" + System.lineSeparator());
		mostUsedWords.forEach(word -> System.out.println(word + System.lineSeparator()));

		if (BooleanUtils.toBoolean(System.getProperty("translate"))) {
			GoogleTranslationService translationService = new GoogleTranslationService();
			translationService.translateReviews(parsedFile);
		}
	}
}
