package ui_private.features.sliders;

import state_public.StateManager;

public class PenThicknessSlider extends SlogoSlider {

    private static final String TITLE = "Pen Thickness";

    public PenThicknessSlider(StateManager manager) {
        super(manager);
    }

    @Override
    protected void handleItemSelected(Number item) {
        System.out.println(item);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

}
