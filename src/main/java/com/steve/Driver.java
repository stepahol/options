package com.steve;

import com.steve.parsing.*;
import com.steve.parsing.lexer.Lexer;

import java.util.HashMap;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        Option option_scheme = new OptionAlternative(
                new OptionSequence(
                        new OptionValued("-d"),
                        new OptionValued("-m")
                ),
                new OptionValued("-h", true)
        );
        System.out.println(option_scheme);
        OptionParser optionParser = new OptionParser(args,option_scheme);

        boolean successfully_parsed = optionParser.parse();
        HashMap<String, String> results = optionParser.getResults().getRawMap();

        System.out.println(successfully_parsed);
        results.forEach((key, value) -> System.out.println("!" + key + "!, !" + value + "!"));
//        Lexer.parseString("val(-d)");
    }
}
