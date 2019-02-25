package frontend;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class DropBox {

    protected ComboBox<String> myDropBox;

    public DropBox(HBox hbox, String s, ObservableList<String> list) {
        myDropBox = new ComboBox<String>();
        myDropBox.setPromptText(s);
        myDropBox.setEditable(true);
        myDropBox.setVisibleRowCount(3);
        myDropBox.setItems(list);
        hbox.getChildren().add(myDropBox);
    }

}
