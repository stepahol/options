package com.steve.parsing.lexer;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sholopkin on 09.06.2017.
 */
public class LexerTest extends Assert {
    @Test
    public void testGetFunctionArguments_argWithoutNesting() {
        String line = "sum(a, b)";

        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String resultFunction = result.getFunction();
        List<String> resultArguments = result.getArguments();

        assertEquals(resultFunction, "sum");
        assertEquals(resultArguments.size(), 2);
        assertEquals(resultArguments.get(0), "a");
        assertEquals(resultArguments.get(1), "b");
    }

    @Test
    public void testGetFunctionArgument_argWithNesting1() {
        String line = "sum(calc(a), b)";

        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String resultFunction = result.getFunction();
        List<String> resultArguments = result.getArguments();

        assertEquals(resultFunction, "sum");
        assertEquals(resultArguments.size(), 2);
        assertEquals(resultArguments.get(0), "calc(a)");
        assertEquals(resultArguments.get(1), "b");
    }

    @Test
    public void testGetFunctionArgument_argWithNesting2() {
        String line = "sum(a, val(b))";

        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String resultFunction = result.getFunction();
        List<String> resultArguments = result.getArguments();

        assertEquals(resultFunction, "sum");
        assertEquals(resultArguments.size(), 2);
        assertEquals(resultArguments.get(0), "a");
        assertEquals(resultArguments.get(1), "val(b)");


    }

    @Test
    public void testGetFunctionArgument_argEmpty1() {
        try {
            String line = "sum(a, )";
            Lexer.getFunctionArguments(line);
            Assert.fail("No exception is thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Lexer.ERR_EMPTY_ARG);
        }
    }

    @Test
    public void testGetFunctionArgument_argEmpty2() {
        try {
            String line = "sum()";
            Lexer.getFunctionArguments(line);
            Assert.fail("No exception is thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Lexer.ERR_EMPTY_ARG);
        }
    }

    @Test
    public void testGetFunctionArgument_notAFunction() {
        try {
            String line = "sum";
            Lexer.getFunctionArguments(line);
            Assert.fail("No exception is thrown");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Lexer.ERR_NOT_A_FUNCTION);
        }
    }

    @Test
    public void testGetFunctionArgument_parsesWithSpaces() {
        String line = "sum     (    f (     a    ),  g(     b    )    )";
        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String function = result.getFunction();
        List<String> arguments = result.getArguments();
        assertEquals(function, "sum");
        assertEquals(arguments.size(), 2);
        assertEquals(arguments.get(0), "f (     a    )");
        assertEquals(arguments.get(1), "g(     b    )");
    }

    @Test
    public void testGetFunctionArgument_passeWithNestedFunctions() {
        String line = "sum(f(g(a, b), h(c, d)), m(a,b))";
        FunctionArgsContainer result = Lexer.getFunctionArguments(line);
        String function = result.getFunction();
        List<String> arguments = result.getArguments();
        assertEquals(function, "sum");
        assertEquals(arguments.size(), 2);
        assertEquals(arguments.get(0), "f(g(a, b), h(c, d))");
        assertEquals(arguments.get(1), "m(a,b)");
    }
}
