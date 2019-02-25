package backendapi;

import parser_public.CommandParser;
import java.util.List;

public class HistoryCall {

    public HistoryCall(String program) {}

    public List<String> call() {
        return CommandParser.getInstance().getCommandHistory();
    }
}
