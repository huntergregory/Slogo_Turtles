package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;

/**
 * This class is used to select past commands. The selected command populates the CommandTerminal
 * @author Carter Gay
 */
public class PastCommandsSelector extends Selector {

    /**
     * Creates binding between UserCommandsSelector and StateManager
     * @param manager
     */
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
}
