package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;
import state.Turtle;

public class PenColorSelector extends Selector {

    public PenColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return myStateManager.getPaletteManager().getPaletteList();
    }

    @Override
    protected void handleItemSelected(String item) {
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[0]);
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setColor(myStateManager.getPaletteManager().getPalette(myIndex));
        }
    }
}
