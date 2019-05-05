package ui_private.features.addition;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import state.Turtle;
import state.TurtleManager;


public class TurtleIcon {
    private static final String TURTLE = "Turtle ";
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;

    private Turtle myTurtle;
    private ImageView myImageView;
    private VBox myVBox;
    private Label myLabel;
    private ComboBox<String> myComboBox;

    protected TurtleIcon(Turtle turtle) {
        myTurtle = turtle;
        myLabel = new Label(TURTLE + turtle.getID());
        initImageView();
        initComboBox();
        myVBox = new VBox(myLabel, myImageView, myComboBox);
        myVBox.setAlignment(Pos.CENTER);
    }

    private void initComboBox() {
        myComboBox = new ComboBox<>(TurtleManager.TURTLE_IMAGE_FILES);
        myComboBox.getSelectionModel().select(myTurtle.getImageProperty().get());
        myComboBox.setEditable(false);
        myComboBox.valueProperty().addListener((o, old, neww) -> myTurtle.getImageProperty().set(neww));
    }

    private void initImageView() {
        var defaultImage = TurtleManager.TURTLE_IMAGE_FILES.get(0);
        myImageView = new ImageView(getImage(defaultImage));
        myImageView.setPreserveRatio(false);
        myImageView.setFitWidth(WIDTH);
        myImageView.setFitHeight(HEIGHT);
        myTurtle.getImageProperty().addListener((o, old, neww) -> myImageView.setImage(getImage(neww)));
    }

    private Image getImage(String fileName) {
        return new Image(getClass().getClassLoader().getResourceAsStream(fileName)); //assuming files map to real images
    }

    protected Node getIcon() {
        return myVBox;
    }
}
