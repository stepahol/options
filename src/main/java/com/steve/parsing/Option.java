package com.steve.parsing;

/**
 * Created by sholopkin on 29.05.2017.
 */
public abstract class Option {
    public abstract boolean canBeParsed(ParsingState state);
    public abstract boolean parse(ParsingState state, OptionResultSet results);
}
