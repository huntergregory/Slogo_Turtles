package frontend;

import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SlogoButton {

    protected Button myButton;
    protected Font font = new Font("verdana",12);

    public SlogoButton(HBox hbox, String s) {
        myButton = new Button(s);
        myButton.setFont(font);
        hbox.getChildren().add(myButton);
    }

}
