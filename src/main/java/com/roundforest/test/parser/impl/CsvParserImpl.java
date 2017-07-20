package com.roundforest.test.parser.impl;

import com.roundforest.test.parser.CsvParser;
import com.roundforest.test.parser.entity.ReviewEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CsvParserImpl implements CsvParser {
    private static final String SEPARATOR = ",+(?=[^\"]*(\"[^\"]*\"[^\"]*)*$)";
    private static final int COLUMNS_AMOUNT = 10;

    public Set<ReviewEntity> parseFile(File file) {
        Set<ReviewEntity> rows = new HashSet<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (line.startsWith("\"")) {
                    line = line.substring(1, line.length() - 1);
                }
                String[] columns = line.split(SEPARATOR, 10);
                if (isValidRow(columns)) {
                    rows.add(readRowEntity(columns));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }

    private ReviewEntity readRowEntity(String[] columns) {
        return new ReviewEntity.RowEntityBuilder()
                .id(columns[0])
                .productId(columns[1])
                .userId(columns[2])
                .profileName(columns[3])
                .helpfulnessNumerator(Integer.parseInt(columns[4]))
                .helpfulnessDenominator(Integer.parseInt(columns[5]))
                .score(Integer.parseInt(columns[6]))
                .date(new Date(Long.parseLong(columns[7])))
                .summary(columns[8])
                .text(columns[9])
                .build();
    }

    private boolean isValidRow(String[] columns) {
        return columns.length == COLUMNS_AMOUNT &&
                NumberUtils.isCreatable(columns[4]) && NumberUtils.isCreatable(columns[5]) &&
                NumberUtils.isCreatable(columns[6]);
    }
}
