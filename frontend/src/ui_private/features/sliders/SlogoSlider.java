package ui_private.features.sliders;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import ui_private.features.Feature;

public abstract class SlogoSlider extends Feature {

    private Slider mySlider;

    public SlogoSlider() {
        super(true);
        mySlider = new Slider(0, 1, 0.5);
        mySlider.setShowTickMarks(true);
        mySlider.setShowTickLabels(true);
        mySlider.valueProperty().addListener((ov, old_val, new_val) -> handleItemSelected(new_val));
    }

    abstract protected void handleItemSelected(Number item);

    @Override
    public Node getMainComponent() {
        return mySlider;
    }
}
