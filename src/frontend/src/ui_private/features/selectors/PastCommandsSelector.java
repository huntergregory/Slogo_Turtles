package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class PastCommandsSelector extends Selector {
    private static final String TITLE = "Past Commands";
    private static final ObservableList PASTCOMMANDS = FXCollections.observableArrayList("","fd 50","bk 100");

    public PastCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return PASTCOMMANDS;
    }

    @Override
    protected void handleItemSelected(String item) {
        System.out.println(item);
        myCommandTerminal.setText(item);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
