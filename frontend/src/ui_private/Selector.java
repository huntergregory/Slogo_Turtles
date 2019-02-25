package ui_private;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

class Selector {

    private Font font = new Font("verdana",12);
    private Button myButton;
    private ComboBox<String> myDropBox;

    Selector(VBox controlBox, String buttonText, String dropBoxText, ObservableList<String> list) {
        HBox hbox = new HBox();
        myDropBox = new ComboBox<String>();
        myDropBox.setPromptText(dropBoxText);
        myDropBox.setEditable(true);
        myDropBox.setVisibleRowCount(3);
        myDropBox.setItems(list);
        hbox.getChildren().add(myDropBox);

        myButton = new Button(buttonText);
        myButton.setFont(font);
        hbox.getChildren().add(myButton);
        controlBox.getChildren().add(hbox);

    }

    Button getButton() {
        return myButton;
    }

    String getInput() {
        return myDropBox.getValue();
    }

}
