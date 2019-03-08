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
import javafx.scene.paint.Color;
import state_public.Palette;
import state_public.StateManager;
import state_public.Turtle;
import ui_private.displays.CommandTerminal;

import java.awt.*;
import java.lang.reflect.Field;

public class PenColorSelector extends Selector {
    private static final ObservableList PENCOLORS = FXCollections.observableArrayList("PINK 1", "BLUE 2", "GREEN 3", "WHITE 4", "BLACK 5");

    public PenColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return PENCOLORS;
    }

    @Override
    protected void handleItemSelected(String item) {
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[1]);
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setColor(myStateManager.getPaletteManager().getPalette(myIndex));
        }
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

}
