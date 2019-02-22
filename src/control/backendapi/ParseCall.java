package control.backendapi;

import parser.CommandParser;
import parser.ParserException;

public class ParseCall extends BackendAPICall {

    private String program;

    public ParseCall(String program) {
        this.program = program;
    }

    @Override
    public double call() throws ParserException {
        CommandParser.getInstance().parseAndRun(program);
        return 0;
    }
}
