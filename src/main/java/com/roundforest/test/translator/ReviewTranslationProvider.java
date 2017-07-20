package com.roundforest.test.translator;


import com.roundforest.test.translator.domain.RequestItem;
import com.roundforest.test.translator.domain.ResponseItem;
import com.roundforest.test.translator.utils.TranslationStringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class ReviewTranslationProvider implements Callable<String> {

    private RestTemplate restTemplate = new RestTemplate();
    private RequestItem requestItem;

    ReviewTranslationProvider(RequestItem requestItem) {
        this.requestItem = requestItem;
    }

    @Override
    public String call() throws Exception {
        //I was not sure what is meant by "mocking" endpoint, so thread will just wait 200 ms (avg response time) and
        //return input data.
/*
        ResponseItem response =
                restTemplate.postForObject(TranslationStringUtils.URL, requestItem, ResponseItem.class);
        return response.getText();
*/
        Thread.sleep(200);
        return requestItem.getText();
    }
}
