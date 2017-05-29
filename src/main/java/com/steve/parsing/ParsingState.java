package com.steve.parsing;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class ParsingState {
    private String[] args;
    private int counter;

    public ParsingState(String[] args) {
        this.args = args;
        counter = 0;
    }

    private String getArg(int i) {
        if (i > -1 && i < args.length) {
            return args[i];
        } else {
            return "";
        }
    }

    public void next(int step) {
        counter += step;
    }

    public String getCurrArg() { return getArg(counter); }
    public String getNextArg() { return getArg(counter + 1); }
}
