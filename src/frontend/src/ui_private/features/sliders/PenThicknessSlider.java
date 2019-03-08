package ui_private.features.sliders;

import state_public.StateManager;
import state_public.Turtle;

public class PenThicknessSlider extends SlogoSlider {

    private static final String TITLE = "Pen Thickness";

    public PenThicknessSlider(StateManager manager) {
        super(manager);
    }

    @Override
    protected void handleItemSelected(Number item) {
        System.out.println(item);
        double myThickness = item.doubleValue();
        for(Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            turtle.getPen().setThickness(myThickness);
        }

    }

}
