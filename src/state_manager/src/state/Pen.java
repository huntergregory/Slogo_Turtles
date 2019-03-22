package state;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

/**
 * Class to represent a pen
 * @author David Miron
 */
public class Pen {

    private static final int DEFAULT_THICKNESS = 3;
    private static final boolean DEFAULT_IS_DOWN = true;
    private static final boolean DEFAULT_SHOULD_ERASE = false;

    private SimpleObjectProperty<Palette> myColorProperty;
    private SimpleDoubleProperty myThicknessProperty;
    private SimpleBooleanProperty myIsDownProperty;
    private SimpleBooleanProperty myShouldEraseLinesProperty;
    private SimpleListProperty<Double> myStrokesProperty;
    private Palette myPalette;
    private PaletteManager myPaletteManager;

    public Pen(Palette color) {
        myColorProperty = new SimpleObjectProperty<>(color);
        myPalette = color;
        myPaletteManager = new PaletteManager();
        myThicknessProperty = new SimpleDoubleProperty(DEFAULT_THICKNESS);
        myIsDownProperty = new SimpleBooleanProperty(DEFAULT_IS_DOWN);
        myShouldEraseLinesProperty = new SimpleBooleanProperty(DEFAULT_SHOULD_ERASE);
        myStrokesProperty = new SimpleListProperty<>();
    }

    /**
     * Erase all lines on the screen
     */
    public void eraseLines() {
        myShouldEraseLinesProperty.set(true);
        myShouldEraseLinesProperty.set(false); // Reset to false after listener deletes lines
    }

    /**
     * Set the pen color to a particular index
     * @param index The index of the color
     */
    public void setPenColor(int index) {
        myPalette = myPaletteManager.getPalette(index);
    }

    /**
     * Set the color to a given palette
     * @param color The color
     */
    public void setColor(Palette color) {
        myColorProperty.set(color);
    }

    /**
     * Set the thickness of the pen
     * @param thickness The thickness
     */
    public void setThickness(double thickness) {
        myThicknessProperty.set(thickness);
    }

    /**
     * Set if the pen is down
     * @param isDown If the pen is down
     */
    public void setIsDown(boolean isDown) {
        myIsDownProperty.set(isDown);
    }

    /**
     * Set the strokes of the pen
     * @param strokes The strokes
     */
    public void setStrokes(Double[] strokes) {
        myStrokesProperty.set(FXCollections.observableArrayList(strokes));
    }

    /**
     * Get the current palette
     * @return The current palette
     */
    public SimpleObjectProperty<Palette> getPaletteProperty() {
        return myColorProperty;
    }

    /**
     * Get the current thickness
     * @return The current thickness
     */
    public SimpleDoubleProperty getThicknessProperty() {
        return myThicknessProperty;
    }

    /**
     * Get if the pen is down
     * @return True if the pen is down, false otherwise
     */
    public SimpleBooleanProperty getIsDownProperty() {
        return this.myIsDownProperty;
    }

    /**
     * Get the erase property
     * @return The erase property
     */
    public BooleanProperty getEraseProperty() {
        return myShouldEraseLinesProperty;
    }

    /**
     * Get if the pen is down
     * @return True if the pen is down, false otherwise
     */
    public boolean getIsDown() {
        return myIsDownProperty.get();
    }

    /**
     * Get the color of the pen
     * @return The color of the pen
     */
    public Palette getColor() {
        return myColorProperty.get();
    }

    /**
     * Get the strokes property
     * @return The strokes property
     */
    public SimpleListProperty<Double> getStrokesProperty() {
        return myStrokesProperty;
    }
}
