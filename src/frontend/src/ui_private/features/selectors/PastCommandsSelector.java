package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;

/**
 * @author Carter Gay
 * @author Harry Ross
 */
class PastCommandsSelector extends Selector {

    PastCommandsSelector(StateManager manager) {
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
}
