package control.backendapi;

import parser.CommandParser;
import parser.ParserException;

public class ParseCall {

    private String program;

    public ParseCall(String program) {
        this.program = program;
    }

    public void call() throws ParserException {
        CommandParser.getInstance().parseAndRun(program);
    }
}
