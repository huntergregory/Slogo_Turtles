package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import state_public.Turtle;
import ui_private.displays.CommandTerminal;

public class PenUpDownSelector extends Selector {
    private  ObservableList PENSTATES = FXCollections.observableArrayList("", "UP", "DOWN");
    private String myPenState;

    public PenUpDownSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return PENSTATES;
    }

    @Override
    protected void handleItemSelected(String item) {
        boolean isDown;
        if (item == "UP") {
            isDown = true;
        }
        else  {
            isDown = false;
        }
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setIsDown(isDown);
        }
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    protected String getPenColor() {
        return myPenState;
    }
}