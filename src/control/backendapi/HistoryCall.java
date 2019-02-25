package control.backendapi;

import parser.CommandParser;
import java.util.List;

public class HistoryCall extends BackendAPICall {

    public HistoryCall(String program) {}

    @Override
    public List<String> call() {
        return CommandParser.getInstance().getCommandHistory();
    }
}
