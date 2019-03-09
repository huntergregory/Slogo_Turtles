package ui_private.features.sliders;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import state.StateManager;
import ui_private.features.Feature;

public abstract class SlogoSlider extends Feature {
    protected static final double[] DEFAULT_MIN_MAX_CURRENT = {0, 5, 1};

    private Slider mySlider;

    public SlogoSlider(StateManager manager) {
        super(manager);
        double[] minMaxCurrent = getMinMaxCurrentVals();
        if (minMaxCurrent == null || minMaxCurrent.length != 3)
            minMaxCurrent = DEFAULT_MIN_MAX_CURRENT;
        mySlider = new Slider(minMaxCurrent[0], minMaxCurrent[1], minMaxCurrent[2]);
        mySlider.setShowTickMarks(true);
        mySlider.setShowTickLabels(true);
        mySlider.valueProperty().addListener((ov, old_val, new_val) -> handleItemSelected(new_val));
    }

    abstract protected void handleItemSelected(Number item);

    abstract protected double[] getMinMaxCurrentVals();

    @Override
    public Node getMainComponent() {
        return mySlider;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return true; }
}
