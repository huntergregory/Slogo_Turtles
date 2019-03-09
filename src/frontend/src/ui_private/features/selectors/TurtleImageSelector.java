package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import state.Turtle;

public class TurtleImageSelector extends Selector {
    private static final ObservableList TURTLEIMAGES = FXCollections.observableArrayList("tan_turtle.png", "green_turtle.png", "red_turtle.png");

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
