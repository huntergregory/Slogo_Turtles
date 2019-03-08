package ui_private.features.sliders;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import state_public.StateManager;
import ui_private.features.Feature;

public abstract class SlogoSlider extends Feature {
    private Slider mySlider;

    public SlogoSlider(StateManager manager) {
        super(manager);
        mySlider = new Slider(0, 5, 1);
        mySlider.setShowTickMarks(true);
        mySlider.setShowTickLabels(true);
        mySlider.valueProperty().addListener((ov, old_val, new_val) -> handleItemSelected(new_val));
    }

    abstract protected void handleItemSelected(Number item);

    @Override
    public Node getMainComponent() {
        return mySlider;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return true; }
}
