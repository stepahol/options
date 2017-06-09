package com.steve.parsing;

import java.util.List;

/**
 * Created by sholopkin on 29.05.2017.
 */
public abstract class Option {
    public abstract boolean canBeParsed(ParsingState state);
    public abstract boolean parse(ParsingState state, OptionResultSet results);

    protected static String getFunctionStringRepr(String function, List<Option> options) {
        StringBuilder result = new StringBuilder();
        result.append(function).append("(");
        for (int i = 0; i < options.size(); ++i) {
            result.append(options.get(i));
            if (i != options.size() - 1) {
                result.append(", ");
            }
        }
        result.append(")");
        return result.toString();
    }
}
