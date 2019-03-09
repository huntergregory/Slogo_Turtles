package state;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author David Miron
 */
public class CommandHistory {

    private ListProperty<String> myHistory;

    public CommandHistory() {
        myHistory = new SimpleListProperty<>();
        myHistory.set(FXCollections.observableArrayList());
    }

    public void addCommand(String program) {
        ObservableList<String> observableList = myHistory.getValue();
        observableList.remove(program);
        observableList.add(program);
        myHistory.set(observableList);
    }

    public ListProperty getCommandsProperty() {
        return myHistory;
    }
}
