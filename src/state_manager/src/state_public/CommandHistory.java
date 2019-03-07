package state_public;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private ListProperty<String> myHistory;

    public CommandHistory() {
        myHistory = new SimpleListProperty<>();
    }

    public void addCommand(String program) {
        myHistory.add(program);
    }

    public ListProperty getCommandsProperty() {
        return myHistory;
    }
}
