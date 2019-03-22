package ui_private.features.sliders;

import state.StateManager;
import state.Turtle;

/**
 *
 * @author Hunter Gregory
 */
public class AnimationDurationSlider extends SlogoSlider {
    public AnimationDurationSlider(StateManager manager) {
        super(manager);
    }

    @Override
    protected void handleItemSelected(Number item) {
        for (Turtle turtleStates : myStateManager.getTurtleManager().getTurtles())
            turtleStates.setAnimationDuration(item.doubleValue());
        System.out.println("here");
    }

    @Override
    protected double[] getMinMaxCurrentVals() {
        return new double[]{1, 500, 1};
    }
}
