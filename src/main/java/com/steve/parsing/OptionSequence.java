package com.steve.parsing;

import java.util.HashMap;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionSequence extends Option {
    private Option[] options;

    public OptionSequence(Option ... options) {
        this.options = options;
    }

    @Override
    public boolean canBeParsed(ParsingState state) {
        return options.length > 0 && options[0].canBeParsed(state);
    }

    @Override
    public boolean parse(ParsingState state, HashMap<String, String> results) {
        boolean successfully_parsed = true;
        for (Option option : options) {
            successfully_parsed = successfully_parsed && option.parse(state, results);
        }
        return successfully_parsed;
    }
}
