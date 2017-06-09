package com.steve.parsing;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionSequence extends Option {
    private List<Option> options;

    public OptionSequence(Option ... options) {
        this.options = Arrays.asList(options);
    }

    public OptionSequence(List<Option> options) { this.options = options; }

    @Override
    public boolean canBeParsed(ParsingState state) {
        return options.size() > 0 && options.get(0).canBeParsed(state);
    }

    @Override
    public void parse(ParsingState state, OptionResultSet results) {
        for (Option option : options) {
            option.parse(state, results);
        }
    }

    @Override
    public String toString() {
        return getFunctionStringRepr("seq", options);
    }
}
