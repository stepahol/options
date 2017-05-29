package com.steve.parsing;

import java.util.HashMap;

/**
 * Created by sholopkin on 29.05.2017.
 */
public abstract class Option {
    public abstract boolean canBeParsed(ParsingState state);
    public abstract boolean parse(ParsingState state, HashMap<String, String> results);
}
