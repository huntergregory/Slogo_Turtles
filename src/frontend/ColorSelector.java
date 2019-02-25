package frontend;

import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class ColorSelector {

    private Font font = new Font("verdana",12);
    private Button myButton;
    private ColorPicker myColorPicker;

    ColorSelector(VBox controlBox, String buttonText) {
        HBox hBox = new HBox();
        myColorPicker = new ColorPicker();
        hBox.getChildren().add(myColorPicker);
        myButton = new Button(buttonText);
        myButton.setFont(font);
        hBox.getChildren().add(myButton);
        controlBox.getChildren().add(hBox);
    }

    Button getButton() {
        return myButton;
    }

    Color getColor() {
        return myColorPicker.getValue();
    }

}
