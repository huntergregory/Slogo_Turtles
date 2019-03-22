package state;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Hold the history of commands run
 * @author David Miron
 */
public class CommandHistory {

    private ListProperty<String> myHistory;

    public CommandHistory() {
        myHistory = new SimpleListProperty<>();
        myHistory.set(FXCollections.observableArrayList());
    }

    /**
     * Add a command that was run
     * @param program The command
     */
    public void addCommand(String program) {
        ObservableList<String> observableList = myHistory.getValue();
        observableList.remove(program);
        observableList.add(program);
        myHistory.set(observableList);
    }

    /**
     * Get the command history
     * @return The command history
     */
    public ListProperty getCommandsProperty() {
        return myHistory;
    }
}
