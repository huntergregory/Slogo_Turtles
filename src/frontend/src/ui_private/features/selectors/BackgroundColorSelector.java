package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;

/**
 * This class is used to select the background color of the TurtleDisplay
 * @author Carter Gay
 */
public class BackgroundColorSelector extends Selector {

    /**
     * Creates binding between BackgroundColorSelector and StateManager
     * @param manager
     */
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
}