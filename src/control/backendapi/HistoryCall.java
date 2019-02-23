package control.backendapi;

import parser.CommandParser;
import parser.ParserException;

public class HistoryCall extends BackendAPICall {

    public HistoryCall(String program) {}

    @Override
    public double call() throws ParserException {
        CommandParser.getInstance().getCommandHistory();
        return 0;
    }
}
