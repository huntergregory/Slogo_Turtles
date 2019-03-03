package state_public;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private List<String> myHistory;

    public CommandHistory() {
        myHistory = new ArrayList<>();
    }

    public void addCommand(String program) {
        myHistory.add(program);
    }
}
