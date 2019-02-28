package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import ui_private.displays.TurtleDisplay;

import java.io.InputStream;
import java.util.ArrayList;

public class TurtleImageSelector extends Selector<Image> {
    private static final String TITLE = "Turtle Image";
    private static final String[] IMAGE_NAMES = {"tan_turtle.png"}; //TODO: include names
    private ObservableList IMAGES = constructList(); //can't be static because getClass can't be in a static method

    private ObservableList constructList() {
        ArrayList<Image> images = new ArrayList<>();
        for (String imageName : IMAGE_NAMES) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(imageName);
            System.out.println(stream == null);
            if (stream != null)
                images.add(new Image(stream));
        }
        return FXCollections.observableArrayList(images);
    }

    @Override
    protected ObservableList getItemList() {
        return null;
    }

    @Override
    protected EventHandler<ActionEvent> handleItemSelected(Image item) {
        return event -> {}; //turtleDisplay.setImage(item) //TODO: get turtle display and change image
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
