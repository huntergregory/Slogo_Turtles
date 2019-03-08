package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import state_public.Turtle;
import ui_private.ResourceBundleHelper;
import ui_private.displays.CommandTerminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class LineStrokeSelector extends Selector {
    private static final String STROKE_PROPERTIES = "line_stroke";
    private static final ResourceBundleHelper myResourceHelper = new ResourceBundleHelper(STROKE_PROPERTIES);
    private static final ObservableList STROKES = FXCollections.observableArrayList(myResourceHelper.getAllLabels());

    public LineStrokeSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return STROKES;
    }

    @Override
    protected void handleItemSelected(String item) {
        try {
            String[] strokeStrings = myResourceHelper.getInfo(item).split(", ");
            Double[] strokes = new Double[strokeStrings.length];
            for (int k = 0; k < strokes.length; k++) {
                strokes[k] = Double.parseDouble(strokeStrings[k]);
            }

            for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
                turtle.getPen().setStrokes(strokes);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Couldn't parse stroke array. Check properties file");
        }
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
