package state_public;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Pen {

    private static final int DEFAULT_THICKNESS = 3;
    private static final boolean DEFAULT_IS_DOWN = true;
    private static final boolean DEFAULT_SHOULD_ERASE = false;

    private SimpleObjectProperty<Palette> myColorProperty;
    private SimpleIntegerProperty myThicknessProperty;
    private SimpleBooleanProperty myIsDownProperty;
    private SimpleBooleanProperty myShouldEraseLinesProperty;

    public Pen(Palette color) {
        myColorProperty = new SimpleObjectProperty<>(color);
        myThicknessProperty = new SimpleIntegerProperty(DEFAULT_THICKNESS);
        myIsDownProperty = new SimpleBooleanProperty(DEFAULT_IS_DOWN);
        myShouldEraseLinesProperty = new SimpleBooleanProperty(DEFAULT_SHOULD_ERASE);
    }

    public void eraseLines() {
        myShouldEraseLinesProperty.set(true);
        myShouldEraseLinesProperty.set(false); // Reset to false after listener deletes lines
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

    public SimpleObjectProperty<Palette> getPaletteProperty() {
        return myColorProperty;
    }

    public SimpleIntegerProperty getThicknessProperty() {
        return myThicknessProperty;
    }

    public SimpleBooleanProperty getIsDownProperty() {
        return this.myIsDownProperty;
    }

    public BooleanProperty getEraseProperty() {
        return myShouldEraseLinesProperty;
    }

    public boolean getIsDown() {
        return myIsDownProperty.get();
    }

    public Palette getColor() {
        return myColorProperty.get();
    }
}
