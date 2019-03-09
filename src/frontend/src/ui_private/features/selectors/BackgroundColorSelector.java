package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;

/**
 * @author Carter Gay
 * @author Harry Ross
 */
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
}