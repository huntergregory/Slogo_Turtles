package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import ui_private.displays.CommandTerminal;

public class PastCommandsSelector extends Selector {
    private static final ObservableList PASTCOMMANDS = FXCollections.observableArrayList("");

    public PastCommandsSelector(StateManager manager) {
        super(manager);
        //setItemList(myStateManager.getCommandHistory().getCommandsProperty());
        myStateManager.getCommandHistory().addCommand("past command!"); //TODO: remove when backend uses this
    }

    @Override
    protected ObservableList getItemList() {
        return PASTCOMMANDS;
    }


    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
        myStateManager.getCommandHistory().addCommand("past command yo!");
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
