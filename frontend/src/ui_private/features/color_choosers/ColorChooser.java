package ui_private.features.color_choosers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import ui_private.features.Feature;
import ui_private.displays.TurtleDisplay;

public abstract class ColorChooser extends Feature {
    private ColorPicker myColorPicker;
    protected TurtleDisplay myTurtleDisplay;

    public ColorChooser() {
        myColorPicker = new ColorPicker();
    }

    public void setTurtleDisplay(TurtleDisplay turtleDisplay) {
        if (turtleDisplay == null)
            return;
        myTurtleDisplay = turtleDisplay;
        addListener();
    }

    private void addListener() {
        myColorPicker.setOnAction(handleNewColor(myColorPicker.getValue())); //not sure if this color will be dynamic or fixed
    }

    abstract EventHandler<ActionEvent> handleNewColor(Color newColor);

    @Override
    public Node getTheNode() {
        return myColorPicker;
    }
}
