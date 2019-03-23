package ui_private.features.sliders;

import state.StateManager;
import state.Turtle;

/**
 *
 * @author Carter Gay
 */
class PenThicknessSlider extends SlogoSlider {

    private static final String TITLE = "Pen Thickness";

    PenThicknessSlider(StateManager manager) {
        super(manager);
    }

    @Override
    void handleItemSelected(Number item) {
        double myThickness = item.doubleValue();
        for(Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setThickness(myThickness);
        }
    }

    @Override
    double[] getMinMaxCurrentVals() {
        return DEFAULT_MIN_MAX_CURRENT;
    }
}
