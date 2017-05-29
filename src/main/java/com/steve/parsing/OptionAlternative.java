package com.steve.parsing;

import java.util.HashMap;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionAlternative extends Option {
    private Option[] options;

    public OptionAlternative(Option ... options) { this.options = options; }

    @Override
    public boolean canBeParsed(ParsingState state) {
        for (Option option : options) {
            if (option.canBeParsed(state)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean parse(ParsingState state, HashMap<String, String> results) {
        for (Option option : options) {
            if (option.canBeParsed(state)) {
                return option.parse(state, results);
            }
        }
        return false;
    }
}
