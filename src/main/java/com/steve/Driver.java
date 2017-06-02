package com.steve;

import com.steve.parsing.*;

import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {
        OptionParser optionParser = new OptionParser(
                args,
                new OptionAlternative(
                    new OptionSequence(
                            new OptionValued("-d"),
                            new OptionValued("-m")
                    ),
                    new OptionValued("-h", true)
                )
        );

        boolean successfully_parsed = optionParser.parse();
        HashMap<String, String> results = optionParser.getResults().getRawMap();

        System.out.println(successfully_parsed);
        results.forEach((key, value) -> System.out.println("!" + key + "!, !" + value + "!"));
    }
}
