package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import state.Turtle;

/**
 * @author Carter Gay
 */
class PenUpDownSelector extends Selector {
    private static final ObservableList PENSTATES = FXCollections.observableArrayList("", "UP", "DOWN");
    private String myPenState;

    PenUpDownSelector(StateManager manager) {
        super(manager);
        myDropBox.getSelectionModel().select("DOWN");
    }

    @Override
    protected ObservableList getItemList() {
        return PENSTATES;
    }

    @Override
    protected void handleItemSelected(String item) {
        boolean isDown;
        if (item == "UP") {
            isDown = false;
        }
        else  {
            isDown = true;
        }
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setIsDown(isDown);
        }
    }

    String getPenState() {
        return myPenState;
    }

}