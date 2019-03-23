package ui_private.features.sliders;

import state.StateManager;
import state.Turtle;

/**
 * This class is used to set the thickness of the pen. The selected thickness applies to all Turtles on the screen.
 * @author Carter Gay
 */
public class PenThicknessSlider extends SlogoSlider {

    private static final String TITLE = "Pen Thickness";

    /**
     * Create binding between PenThicknessSlider and StateManager
     * @param manager
     */
    public PenThicknessSlider(StateManager manager) {
        super(manager);
    }

    @Override
    protected void handleItemSelected(Number item) {
        double myThickness = item.doubleValue();
        for(Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setThickness(myThickness);
        }
    }

    @Override
    protected double[] getMinMaxCurrentVals() {
        return DEFAULT_MIN_MAX_CURRENT;
    }
}
