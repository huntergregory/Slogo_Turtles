package ui_private.features;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import state.Turtle;

class TurtleImages {

    private static final ObservableList TURTLEIMAGES = FXCollections.observableArrayList("Turtle 1.png", "Turtle 2.png", "Turtle 3.png");
    private static final String DEFAULT_IMAGE = "Turtle 1.png";
    private static final String ID = "ID: ";
    private static final double IMAGE_SIZE = 50.0;
    private Turtle myTurtle;
    private ImageView myImageView;
    private HBox myHBox;
    private Label myLabel;
    private ComboBox<String> myComboBox;

    TurtleImages(Turtle turtle) {
        myTurtle = turtle;
        build();
    }

    private void build() {
        myLabel = new Label(ID + myTurtle.getID());
        buildImageView();
        buildComboBox();
        buildHBox();
    }

    private void buildImageView() {
        myImageView = new ImageView(getImage(DEFAULT_IMAGE));
        myImageView.setFitHeight(IMAGE_SIZE);
        myImageView.setFitWidth(IMAGE_SIZE);
        myTurtle.getImageProperty().addListener((o, old, newVal) -> myImageView.setImage(getImage(newVal)));
    }

    private Image getImage(String image) {
        Image newImage;
        newImage = new Image(getClass().getClassLoader().getResourceAsStream(image));
        return newImage;
    }

    private void buildComboBox() {
        myComboBox = new ComboBox<>(TURTLEIMAGES);
        myComboBox.valueProperty().addListener((o, old, newVal) -> myTurtle.getImageProperty().set(newVal));
    }

    private void buildHBox() {
        myHBox = new HBox();
        myHBox.getChildren().add(myLabel);
        myHBox.getChildren().add(myImageView);
        myHBox.getChildren().add(myComboBox);
    }

    Node getMainComponent() {
        return myHBox;
    }
}
