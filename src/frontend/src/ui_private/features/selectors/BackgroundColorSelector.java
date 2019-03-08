package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;
import ui_private.displays.CommandTerminal;

public class BackgroundColorSelector extends Selector {

    public BackgroundColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return myStateManager.getPaletteManager().getPaletteList();
    }

    @Override
    protected void handleItemSelected(String item) {
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[0]);
        myStateManager.setBackgroundColor(myIndex);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}