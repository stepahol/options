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
    public boolean parse(ParsingState state, OptionResultSet results) {
        boolean successfully_parsed = true;
        for (Option option : options) {
            successfully_parsed = successfully_parsed && option.parse(state, results);
        }
        return successfully_parsed;
    }

    @Override
    public String toString() {
        return getFunctionStringRepr("seq", options);
    }
}
