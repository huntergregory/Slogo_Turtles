package ui_private.turtles;

import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import state_public.Palette;

import java.awt.*;
import java.util.ArrayList;


/**
 * Creates or erases lines dynamically based on changes in a turtle's pen states.
 * When the pen thickness, color, stroke, etc. are updated, all the previous lines are updated to reflect the change.
 * @author Hunter Gregory
 */
public class Pen {
    public static final String CSS_TAG = "line";

    private state_public.Pen myPenStates;
    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;

    Pen(ObservableList list, state_public.Pen penStates) {
        myPenStates = penStates;
        myModifiableList = list;
        myLines = new ArrayList<>();
        addListeners();
    }


    protected void draw(double oldX, double oldY, double newX, double newY) {
        if (!myPenStates.getIsDown())
            return;
        Line line = new Line(oldX, oldY, newX, newY);
        line.getStyleClass().add(CSS_TAG);
        myLines.add(line);
        myModifiableList.add(line);
        setStyle();
    }


    protected void erase() {
        myModifiableList.removeAll(myLines);
        myLines = new ArrayList<>();
    }


    private void addListeners() {
        myPenStates.getPaletteProperty().addListener((o, oldPalette, newPalette) -> setPenColor(newPalette));
        myPenStates.getThicknessProperty().addListener((o, oldThickness, newThickness) -> setThickness(newThickness.doubleValue()));
        myPenStates.getEraseProperty().addListener((o, oldBool, newBool) -> { if (newBool) erase(); });
        myPenStates.getStrokesProperty().addListener((o, oldStrokes, newStrokes) -> lineStroke(oldStrokes, newStrokes));
    }

    // Currently uses the methods that loop through every line, even though this is called for just styling one line.
    // This inefficiency won't matter when there are a relatively small number of lines
    //TODO: if user is expected to draw many lines, consider making analogous methods for a single, newly created line
    // instead of all lines
    private void setStyle() {
        System.out.println("PALETTE: " + myPenStates.getPaletteProperty().getValue());
        setPenColor(myPenStates.getPaletteProperty().getValue());
        setThickness(myPenStates.getThicknessProperty().getValue());
        var currentStrokes = myPenStates.getStrokesProperty().getValue();
        lineStroke(currentStrokes, currentStrokes);
    }


    //TODO: make private if we decide on having each turtle have its own pen color
    protected void setPenColor(Palette palette) {
        for (Line line : myLines) {
            var color = palette.getColorProperty().getValue();
            System.out.println("PEN COLOR: " + color);
            line.setStroke(color);
        }
    }


    private void setThickness(double thickness) {
        for (Line line : myLines)
            line.setStrokeWidth(thickness);
    }


    protected void lineStroke(ObservableList<Double> oldStrokes, ObservableList<Double> newStrokes) {
        if (newStrokes != null) {
            for (Line line : myLines) {
                if (oldStrokes != null)
                    line.getStrokeDashArray().removeAll(oldStrokes);
                for (double stroke : newStrokes)
                    line.getStrokeDashArray().add(stroke);
            }
        }
    }
}
