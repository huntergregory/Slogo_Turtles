package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import state.Turtle;

/**
 * This class is used to select an image for the turtle. The selected image applies to all active Turtles
 * @author Carter Gay
 * @author Hunter Gregory
 */
public class TurtleImageSelector extends Selector {
    private static final ObservableList TURTLEIMAGES = FXCollections.observableArrayList("Turtle 1.png", "Turtle 2.png", "Turtle 3.png");

    /**
     * Creates binding between TurtleImageSelector and StateManager
     * @param manager
     */
    public TurtleImageSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return TURTLEIMAGES;
    }

    @Override
    protected void handleItemSelected(String item) {
        for (Turtle turtleStates : myStateManager.getTurtleManager().getTurtles()) {
            turtleStates.setImageProperty(item);
        }
    }
}
