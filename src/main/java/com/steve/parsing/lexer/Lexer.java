package com.steve.parsing.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Степан on 08.06.2017.
 */
public class Lexer {
    public static final String ERR_EMPTY_ARG = "Error: there should no be any empty arguments";
    public static final String ERR_NOT_A_FUNCTION = "Error: string is not a function";

    public static void parseString(String str) {
        String line = "seq(val(-d), val(-m))";
        FunctionArgsContainer container = getFunctionArguments(line);
        System.out.println(container.getFunction());
        for (String curr_arg : container.getArguments()) {
            System.out.println(curr_arg);
        }
    }

    public static FunctionArgsContainer getFunctionArguments(String line) {
        String regex = "^(\\w+)\\s*\\(([\\(\\),\\s\\w\\d-]*)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String function = matcher.group(1);
            String args_str = matcher.group(2);
            List<String> args = Arrays.asList(args_str.split(",", -1));
            List<String> args_trimmed = new ArrayList<>();
            for (String arg : args) {
                args_trimmed.add(arg.trim());
            }
            if (args_trimmed.contains("")) {
                throw new RuntimeException(ERR_EMPTY_ARG);
            }
            return new FunctionArgsContainer(function, args_trimmed);
        } else {
            throw new RuntimeException(ERR_NOT_A_FUNCTION);
        }
    }
}
