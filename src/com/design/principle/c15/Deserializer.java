package com.design.principle.c15;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Map;

/**
 *
 *
 * @author  bingshan
 * @date 2022/12/3 21:48
 */
public class Deserializer {
    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Deserializer() {
        this.gson = new Gson();
    }

    public Map<String, String> deserialize(String text) {
        if (!text.startsWith(IDENTIFIER_STRING)) {
            return Collections.emptyMap();
        }
        String gsonStr = text.substring(IDENTIFIER_STRING.length());
        return gson.fromJson(gsonStr, Map.class);
    }
}
