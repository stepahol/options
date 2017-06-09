package com.steve.parsing;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionAlternative extends Option {
    private List<Option> options;

    public OptionAlternative(Option ... options) { this.options = Arrays.asList(options); }

    public OptionAlternative(List<Option> options) { this.options = options; }

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
    public boolean parse(ParsingState state, OptionResultSet results) {
        for (Option option : options) {
            if (option.canBeParsed(state)) {
                return option.parse(state, results);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getFunctionStringRepr("alt", options);
    }
}
