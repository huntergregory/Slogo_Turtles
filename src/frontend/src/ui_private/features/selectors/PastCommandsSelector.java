package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import ui_private.displays.CommandTerminal;

public class PastCommandsSelector extends Selector {

    public PastCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return myStateManager.getCommandHistory().getCommandsProperty().getValue();
    }


    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
