package com.steve.parsing;

import java.util.HashMap;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionParser {
    private String[] args;
    private ParsingState state;
    private Option schema;
    private HashMap<String, String> parsing_results;

    public OptionParser(String[] args, Option schema) {
        this.args = args;
        state = new ParsingState(args);
        this.schema = schema;
        parsing_results = new HashMap<>();
    }

    public HashMap<String, String> getResults() { return parsing_results; }

    public boolean parse() { return schema.parse(state, parsing_results); }
}