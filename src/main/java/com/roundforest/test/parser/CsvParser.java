package com.roundforest.test.parser;


import com.roundforest.test.parser.entity.ReviewEntity;

import java.io.File;
import java.util.Set;

public interface CsvParser {
    Set<ReviewEntity> parseFile(File file);
}
