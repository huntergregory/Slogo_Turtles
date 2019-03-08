package state_public;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private ListProperty<String> myHistory;

    public CommandHistory() {
        myHistory = new SimpleListProperty<>();
        myHistory.set(FXCollections.observableArrayList());
    }

    public void addCommand(String program) {
        var observableList = myHistory.getValue();
        observableList.add(program);
        myHistory.set(observableList);
    }

    public ListProperty getCommandsProperty() {
        return myHistory;
    }
}
