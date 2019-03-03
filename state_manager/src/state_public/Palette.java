package state_public;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

/**
 * Class to hold a particular color, id, and bindings
 * @author David Miron
 */
public class Palette {

    private static int id_count = 0;

    private SimpleIntegerProperty myId;
    private SimpleObjectProperty<Color> myColor;

    public Palette() {
        this(0, 0, 0);
    }

    public Palette(int red, int green, int blue) {
        this(Color.rgb(red, green, blue));
    }

    public Palette(Color color) {
        myId = new SimpleIntegerProperty(id_count);
        id_count++;
        myColor = new SimpleObjectProperty<>(color);
    }

    public SimpleIntegerProperty getIdProperty() {
        return myId;
    }

    public SimpleObjectProperty<Color> getColorProperty() {
        return myColor;
    }
}
