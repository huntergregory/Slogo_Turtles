package state_public;

import javafx.beans.property.*;

public class Pen {

    public static final int DEFAULT_THICKNESS = 3;
    public static final boolean DEFAULT_IS_DOWN = true;
    public static final boolean DEFAULT_SHOULD_ERASE = false;

    private SimpleObjectProperty<Palette> myColorProperty;
    private SimpleDoubleProperty myThicknessProperty;
    private SimpleBooleanProperty myIsDownProperty;
    private SimpleBooleanProperty myShouldEraseLinesProperty;
    private Palette myPalette;
    private PaletteManager myPaletteManager;

    public Pen(Palette color) {
        myColorProperty = new SimpleObjectProperty<>(color);
        myPalette = color;
        myPaletteManager = new PaletteManager();
        myThicknessProperty = new SimpleDoubleProperty(DEFAULT_THICKNESS);
        myIsDownProperty = new SimpleBooleanProperty(DEFAULT_IS_DOWN);
        myShouldEraseLinesProperty = new SimpleBooleanProperty(DEFAULT_SHOULD_ERASE);
    }

    public void eraseLines() {
        myShouldEraseLinesProperty.set(true);
        myShouldEraseLinesProperty.set(false); // Reset to false after listener deletes lines
    }

    public void setPenColor(int index) {
        myPalette = myPaletteManager.getPalette(index);
    }

    public void setColor(Palette color) {
        myColorProperty.set(color);
    }

    public void setThickness(double thickness) {
        myThicknessProperty.set(thickness);
    }

    public void setIsDown(boolean isDown) {
        myIsDownProperty.set(isDown);
    }

    public SimpleObjectProperty<Palette> getPaletteProperty() {
        return myColorProperty;
    }

    public SimpleDoubleProperty getThicknessProperty() {
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
