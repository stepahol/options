package com.steve.parsing;

import com.steve.parsing.lexer.Lexer;

/**
 * Created by sholopkin on 29.05.2017.
 */
public class OptionParser {
    private String[] args;
    private ParsingState state;
    private Option schema;
    private OptionResultSet parsing_results;
    
    public OptionParser(String[] args, String schema_str) {
        Option schema = Lexer.getOptionSchemaFromString(schema_str);
        init(args, schema);
        parse();
    }

    private void init(String[] args, Option schema) {
        this.args = args;
        state = new ParsingState(args);
        this.schema = schema;
        parsing_results = new OptionResultSet();
    }

    public OptionResultSet getResults() { return parsing_results; }

    private void parse() { schema.parse(state, parsing_results); }
}
