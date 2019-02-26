package parser_public.api;

import parser_public.CommandParser;

import java.util.ArrayList;
import java.util.List;

public class HistoryCall {

    public HistoryCall(String program) {}

    public List<String> call() {
        //return CommandParser.getInstance().getCommandHistory();
        return new ArrayList<>();
    }
}
