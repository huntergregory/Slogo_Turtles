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

    Palette(int id, int red, int green, int blue) {
        this(id, Color.rgb(red, green, blue));
    }

    Palette(int id, Color color) {
        myId = new SimpleIntegerProperty(id);
        myColor = new SimpleObjectProperty<>(color);
    }

    /**
     * Get the ID of a palette
     * @return The ID of a palette
     */
    public SimpleIntegerProperty getIdProperty() {
        return myId;
    }

    /**
     * Get the color of a palette
     * @return The color of a palette
     */
    public SimpleObjectProperty<Color> getColorProperty() {
        return myColor;
    }

    /**
     * Get the ID of a palette
     * @return The ID of a palette
     */
    public int getId() {
        return myId.get();
    }

    @Override
    public String toString() {
        return myId.getValue()+ " - " + formattedRGBValue(myColor.getValue());
    }

    private String formattedRGBValue(Color color) {
        return "RGB(" + (int)(color.getRed() * 255) + ", " + (int)(color.getGreen() * 255)
                + ", " + (int)(color.getBlue() * 255) + ")";
    }

    /**
     * Get the Color of a palette
     * @return The color of a palette
     */
    public Color getColor() {
        return myColor.get();
    }
}
