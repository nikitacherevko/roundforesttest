package com.roundforest.test.translator.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestItem {
    private String inputLang;
    private String outputLang;
    private String text;

    public RequestItem() {
    }

    public RequestItem(String inputLang, String outputLang, String text) {
        this.inputLang = inputLang;
        this.outputLang = outputLang;
        this.text = text;
    }

    @JsonProperty("input_lang")
    public String getInputLang() {
        return inputLang;
    }

    public void setInputLang(String inputLang) {
        this.inputLang = inputLang;
    }

    @JsonProperty("output_lang")
    public String getOutputLang() {
        return outputLang;
    }

    public void setOutputLang(String outputLang) {
        this.outputLang = outputLang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
