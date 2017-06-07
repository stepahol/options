package com.steve.parsing.lexer;

import java.util.List;

/**
 * Created by Степан on 08.06.2017.
 */
public class FunctionArgsContainer {
    private String function;
    private List<String> arguments;

    public FunctionArgsContainer(String function, List<String> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    public FunctionArgsContainer() {
        this.function = null;
        this.arguments = null;
    }

    public String getFunction() { return function; }
    public List<String> getArguments() { return arguments; }
}
