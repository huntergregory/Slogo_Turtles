package ui_private.features.color_choosers;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import ui_private.features.Feature;

public abstract class ColorChooser extends Feature {
    private ColorPicker myColorPicker;

    public ColorChooser() {
        myColorPicker = new ColorPicker();
    }

    @Override
    public Node getNode() {
        return myColorPicker;
    }
}
