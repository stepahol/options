package com.steve.parsing.lexer;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by sholopkin on 09.06.2017.
 */
public class LexerTest extends TestCase {
    public void testGetFunctionArguments() {
        String line = "sum(a, b)";

        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String resultFunction = result.getFunction();
        List<String> resultArguments = result.getArguments();

        assertEquals(resultFunction, "sum");
        assertEquals(resultArguments.size(), 2);
        assertEquals(resultArguments.get(0), "a");
        assertEquals(resultArguments.get(1), "b");
    }
}
