package ui_private.features.sliders;

public class PenThicknessSlider extends SlogoSlider {

    private static final String TITLE = "Pen Thickness";

    @Override
    protected void handleItemSelected(Number item) {
        System.out.println(item);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

}
