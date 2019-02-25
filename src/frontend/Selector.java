package frontend;

import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class Selector {

    private SlogoButton myButton;
    private DropBox myDropBox;

    Selector(VBox LoaderBox, String buttonText, String dropBoxText, ObservableList<String> list) {
        HBox hBox = new HBox();
        myDropBox = new DropBox(hBox, dropBoxText, list);
        myButton = new SlogoButton(hBox, buttonText);
        LoaderBox.getChildren().add(hBox);
    }
}
