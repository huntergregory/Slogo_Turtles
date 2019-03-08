package ui_private.features.selectors;
/*
public class BackgroundColorSelector extends Selector {
    private static final String LABEL_TEXT = "Background Color";

    @Override
    void handleNewColor(Color newColor) {
        System.out.println("background here");
        myTurtleDisplay.setBackgroundColor(newColor);
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
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class BackgroundColorSelector extends Selector {
    private static final ObservableList BACKGROUNDCOLORS =
            FXCollections.observableArrayList("BLACK 0", "PINK 1", "BLUE 2", "GRAY 3");
    private String myBackgroundColor;
    private int myIndex;

    public BackgroundColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return BACKGROUNDCOLORS; //FIXME
    }

    @Override
    protected void handleItemSelected(String item) {
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[1]);
        Color color;
        if (myIndex == 0) {
            color = Color.BLACK;
        }
        else if (myIndex == 1) {
            color = Color.LIGHTPINK;
        }
        else if (myIndex == 2) {
            color = Color.LIGHTBLUE;
        }
        else {
            color = Color.DARKGRAY;
        }
        myStateManager.getBackgroundColor().getColorProperty().set(color);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    protected String getPenColor() {
        return myBackgroundColor;
    }
}