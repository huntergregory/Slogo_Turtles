package parser_public.api;

import parser_public.CommandParser;
import parser_public.ParserException;

public class ParseCall {

    private String program;

    public ParseCall(String program) {
        this.program = program;
    }

    public void call() throws ParserException {
        CommandParser.getInstance().parseAndRun(program);
    }
}
