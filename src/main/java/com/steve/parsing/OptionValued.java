package com.steve.parsing;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionValued extends Option {
    private String option_allowed;
    private boolean is_flag;

    public OptionValued(String option_allowed) {
        this.option_allowed = option_allowed;
        is_flag = false;
    }

    public OptionValued(String option_allowed, boolean is_flag) {
        this.option_allowed = option_allowed;
        this.is_flag = is_flag;
    }

    @Override
    public boolean canBeParsed(ParsingState state) {
        String option = state.getCurrArg();
        return option.equals(option_allowed);
    }

    @Override
    public boolean parse(ParsingState state, OptionResultSet results) {
        String option = state.getCurrArg();
        String value = state.getNextArg();

        if (!is_flag) {
            if (isOption(option) && isValue(value)) {
                results.putOption(option, value);
                state.next(2);
                return true;
            }
        } else {
            if (isOption(option) && !isValue(value)) {
                results.putOption(option);
                state.next(1);
                return true;
            }
        }

        return false;
    }

    private boolean isOption(String str) {
        return str.equals(option_allowed);
    }

    private static boolean isValue(String str) {
        return !str.isEmpty() && !str.startsWith("-");
    }
}
