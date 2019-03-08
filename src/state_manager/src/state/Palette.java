package state;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * Class to hold a particular color, id, and bindings
 * @author David Miron
 */
public class Palette {

    private SimpleIntegerProperty myId;
    private SimpleObjectProperty<Color> myColor;

    public Palette(int id, int red, int green, int blue) {
        this(id, Color.rgb(red, green, blue));
    }

    public Palette(int id, Color color) {
        myId = new SimpleIntegerProperty(id);
        myColor = new SimpleObjectProperty<>(color);
    }

    public SimpleIntegerProperty getIdProperty() {
        return myId;
    }

    public SimpleObjectProperty<Color> getColorProperty() {
        return myColor;
    }

    public int getId() {
        return myId.get();
    }

    public Color getColor() {
        return myColor.get();
    }
}
