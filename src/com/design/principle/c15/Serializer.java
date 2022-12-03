package com.design.principle.c15;

import com.google.gson.Gson;

import java.util.Map;

/**
 *
 *
 * @author  bingshan
 * @date 2022/12/3 21:46
 */
public class Serializer {
    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Serializer() {
        this.gson = new Gson();
    }

    public String serialize(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(IDENTIFIER_STRING);
        textBuilder.append(gson.toJson(object));
        return textBuilder.toString();
    }
}
