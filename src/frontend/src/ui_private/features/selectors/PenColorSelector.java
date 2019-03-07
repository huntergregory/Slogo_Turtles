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
import state_public.StateManager;

public class PenColorSelector extends Selector {
    private static final String TITLE = "Pen Color";
    private static final ObservableList PENCOLORS = FXCollections.observableArrayList("", "RED 1", "BLUE 2", "GREEN 3");
    private String myPenColor;
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
        System.out.println("pen here");
        String[] splitted = item.split("\\s+");
        myPenColor = splitted[0];
        myIndex = Integer.parseInt(splitted[1]);
        //myTurtleDisplay.setPenColor(newColor);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

    protected String getPenColor() {
        return myPenColor;
    }
}
