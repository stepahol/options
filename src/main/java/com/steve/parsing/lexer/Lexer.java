package com.steve.parsing.lexer;

import com.steve.parsing.Option;
import com.steve.parsing.OptionAlternative;
import com.steve.parsing.OptionSequence;
import com.steve.parsing.OptionValued;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Степан on 08.06.2017.
 */
public class Lexer {
    public static final String ERR_EMPTY_ARG = "Error: there should no be any empty arguments";
    public static final String ERR_NOT_A_FUNCTION = "Error: string is not a function";

    public static Option getOptionSchemaFromString(String str) {
        FunctionArgsContainer results = getFunctionArguments(str);
        String function = results.getFunction();
        List<String> arguments = results.getArguments();
        if (function.equals("val") || function.equals("flg")) {
            // check correct
            if (!hasOneElemValue(arguments)) {
                throw new RuntimeException("Error: " + function + " operation allows only one elementary value");
            }
            // parse
            return new OptionValued(arguments.get(0), function.equals("flg"));
        } else if (function.equals("seq")) {
            List<Option> parsed_arguments = parseOptionList(function, arguments);
            return new OptionSequence(parsed_arguments);
        } else if (function.equals("alt")) {
            List<Option> parsed_arguments = parseOptionList(function, arguments);
            return new OptionAlternative(parsed_arguments);
        } else {
            throw new RuntimeException("Error: operation is not supported");
        }
    }

    private static List<Option> parseOptionList(String function, List<String> arguments) {
        // check correct
        if (!allValuesAreNotElem(arguments)) {
            throw new RuntimeException("Error: " + function + "operation does not allow elementary values");
        }

        // parse
        List<Option> parsed_arguments = new ArrayList<>();
        for (String arg : arguments) {
            parsed_arguments.add(getOptionSchemaFromString(arg));
        }
        return parsed_arguments;
    }

    private static boolean hasOneElemValue(List<String> arguments) {
        return arguments.size() == 1 &&
                        isElementaryValue(arguments.get(0));
    }

    private static boolean allValuesAreNotElem(List<String> arguments) {
        boolean allValuesAreNotElem = true;
        for (String arg : arguments) {
            allValuesAreNotElem = allValuesAreNotElem && !isElementaryValue(arg);
        }
        return allValuesAreNotElem;
    }

    public static FunctionArgsContainer getFunctionArguments(String line) {
        String regex = "^(\\w+)\\s*\\(([\\(\\),\\s\\w\\d-]*)\\)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String function = matcher.group(1);
            String args_str = matcher.group(2);
            List<String> args = splitWithoutParenthesis(args_str);
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

    public static List<String> splitWithoutParenthesis(String str) {
        StringBuilder builder = new StringBuilder();
        List<String> result = new ArrayList<>();

        int parenthesis_level = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr_char = str.charAt(i);
            if (curr_char == '(') {
                parenthesis_level++;
            } else if (curr_char == ')') {
                parenthesis_level--;
            }

            if (curr_char == ',') {
                if (parenthesis_level == 0) {
                    result.add(builder.toString());
                    builder.setLength(0);
                } else {
                    builder.append(curr_char);
                }
            } else {
                builder.append(curr_char);
            }
        }
        result.add(builder.toString());

        return result;
    }

    private static boolean isElementaryValue(String str) {
        String regex = "^[\\w\\d-]+$";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        return matcher.find();
    }
}
