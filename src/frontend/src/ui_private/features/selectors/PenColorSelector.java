package ui_private.features.selectors;
/*
public class PenColorSelector extends Selector {
    private static final String LABEL_TEXT = "Pen Color";

    @Override
    void handleNewColor(Color newColor) {
        System.out.println("pen here");
        myTurtleDisplay.setPenColor(newColor);
    }

    @Override
    public String getLabelText() {
        return LABEL_TEXT;
    }
}
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.Palette;
import state_public.StateManager;
import state_public.Turtle;
import ui_private.displays.CommandTerminal;

import java.awt.*;
import java.lang.reflect.Field;

public class PenColorSelector extends Selector {
    private ObservableList PENCOLORS = FXCollections.observableArrayList("", "RED 1", "BLUE 2", "GREEN 3");
    private Color myPenColor;
    private int myIndex;

    public PenColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return PENCOLORS;
    }

    @Override
    protected void handleItemSelected(String item) {
        System.out.println("CHANGING PEN COLOR");
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[1]);
        System.out.println(myIndex);
        for(Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setPenColor(myIndex);
        }
        System.out.println("SUCCESS PEN COLOR");
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    protected Color getPenColor() {
        return myPenColor;
    }
}
