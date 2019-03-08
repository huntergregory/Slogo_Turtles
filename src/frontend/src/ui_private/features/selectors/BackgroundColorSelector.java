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
import state_public.StateManager;

public class BackgroundColorSelector extends Selector {
    private static final String TITLE = "Background Color";
    private static final ObservableList BACKGROUNDCOLORS =
            FXCollections.observableArrayList("","RED 1", "BLUE 2", "GREEN 3");
    private String myBackgroundColor;
    private int myIndex;

    public BackgroundColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return BACKGROUNDCOLORS;
    }

    @Override
    protected void handleItemSelected(String item) {
        System.out.println("background color");
        String[] splitted = item.split("\\s+");
        myBackgroundColor = splitted[0];
        myIndex = Integer.parseInt(splitted[1]);
        System.out.println(myIndex);
        myStateManager.setBackgroundColor(myIndex);
        //myTurtleDisplay.setPenColor(newColor);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

    protected String getPenColor() {
        return myBackgroundColor;
    }
}