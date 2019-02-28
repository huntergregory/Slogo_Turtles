package ui_private.features.color_choosers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import ui_private.displays.TurtleDisplay;
import ui_private.features.HorizontalFeature;

public abstract class ColorChooser extends HorizontalFeature {
    private ColorPicker myColorPicker;

    public ColorChooser() {
        myColorPicker = new ColorPicker();
        addListener();
    }

    private void addListener() {
        myColorPicker.setOnAction(e -> handleNewColor(myColorPicker.getValue())); //not sure if this color will be dynamic or fixed
    }

    abstract void handleNewColor(Color newColor);

    @Override
    public Node getMainComponent() {
        return myColorPicker;
    }
}
