package ui_private.features.selectors;

        import javafx.collections.ObservableList;
        import state.StateManager;

/**
 * This class is used to select user defined commands. The selected command populates the CommandTerminal
 * @author Carter Gay
 */
public class UserCommandsSelector extends Selector {

    /**
     * Creates binding between UserCommandsSelector and StateManager
     * @param manager
     */
    public UserCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return myStateManager.getCommands().getCommandsList().getValue();
    }

    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
    }
}