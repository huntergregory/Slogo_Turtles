package state_public;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Pen {

    public static final int DEFAULT_THICKNESS = 3;
    public static final boolean DEFAULT_ISDOWN = true;

    private SimpleObjectProperty<Palette> myColorProperty;
    private SimpleIntegerProperty myThicknessProperty;
    private SimpleBooleanProperty myIsDownProperty;

    public Pen(Palette color) {
        myColorProperty = new SimpleObjectProperty<>(color);
        myThicknessProperty = new SimpleIntegerProperty(DEFAULT_THICKNESS);
        myIsDownProperty = new SimpleBooleanProperty(DEFAULT_ISDOWN);
    }

    public void setColor(Palette color) {
        myColorProperty.set(color);
    }

    public void setThickness(int thickness) {
        myThicknessProperty.set(thickness);
    }

    public void setIsDown(boolean isDown) {
        myIsDownProperty.set(isDown);
    }

    public SimpleObjectProperty<Palette> getColorProperty() {
        return myColorProperty;
    }

    public SimpleIntegerProperty getThicknessProperty() {
        return myThicknessProperty;
    }

    public SimpleBooleanProperty getIsDownProperty() {
        return this.myIsDownProperty;
    }
}
