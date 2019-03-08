package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import state_public.Turtle;
import ui_private.displays.CommandTerminal;

public class PenUpDownSelector extends Selector {
    private static final String TITLE = "Pen Up/Down";
    private static final ObservableList PENSTATES = FXCollections.observableArrayList("", "UP", "DOWN");
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
            isDown = false;
        }
        else  {
            isDown = true;
        }
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setIsDown(isDown);
        }
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

    protected String getPenColor() {
        return myPenState;
    }
}