package ui_private.features.color_choosers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class BackgroundColorChooser extends ColorChooser {
    private static final String LABEL_TEXT = "Background Color";

    @Override
    EventHandler<ActionEvent> handleNewColor(Color newColor) {
        return event -> { System.out.println("background here"); myTurtleDisplay.setBackgroundColor(newColor); };
    }

    @Override
    public String getLabelText() {
        return LABEL_TEXT;
    }
}