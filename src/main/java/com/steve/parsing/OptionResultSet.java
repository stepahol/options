package com.steve.parsing;

import java.util.HashMap;

/**
 * Created by Степан on 02.06.2017.
 */
public class OptionResultSet {
    private HashMap<String, String> results;

    public OptionResultSet() {
        results = new HashMap<>();
    }

    public void putOption(String option, String value) {
        results.put(option, value);
    }

    public void putOption(String option) {
        putOption(option, "FLAG");
    }

    public HashMap<String, String> getRawMap() {
        return results;
    }
}
