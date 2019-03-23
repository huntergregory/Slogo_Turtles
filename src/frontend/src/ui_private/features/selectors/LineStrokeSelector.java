package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;
import state.Turtle;
import ui_private.ResourceBundleHelper;

/**
 * @author Carter Gay
 */
class LineStrokeSelector extends Selector {
    private static final String STROKE_PROPERTIES = "line_stroke";
    private static final ResourceBundleHelper myResourceHelper = new ResourceBundleHelper(STROKE_PROPERTIES);
    private static final ObservableList STROKES = FXCollections.observableArrayList(myResourceHelper.getAllLabels());

    LineStrokeSelector(StateManager manager) {
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
}
