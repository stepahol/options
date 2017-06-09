package com.steve;

import com.steve.parsing.*;
import com.steve.parsing.lexer.FunctionArgsContainer;
import com.steve.parsing.lexer.Lexer;

import java.util.HashMap;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
//        options(args);
        parser(args);
    }

    private static void parser(String[] args) {
        FunctionArgsContainer parse_result = Lexer.getFunctionArguments(args[0]);
        String function = parse_result.getFunction();
        List<String> arguments = parse_result.getArguments();
        System.out.println("Function: !" + function + "!");
        for (String arg : arguments) {
            System.out.println("Argument: !" + arg + "!");
        }
    }

    private static void options(String[] args) {
        Option option_scheme = new OptionAlternative(
                new OptionSequence(
                        new OptionValued("-d"),
                        new OptionValued("-m")
                ),
                new OptionValued("-h", true)
        );
        OptionParser optionParser = new OptionParser(args,option_scheme);

        boolean successfully_parsed = optionParser.parse();
        HashMap<String, String> results = optionParser.getResults().getRawMap();

        System.out.println(successfully_parsed);
        results.forEach((key, value) -> System.out.println("!" + key + "!, !" + value + "!"));
    }
}
