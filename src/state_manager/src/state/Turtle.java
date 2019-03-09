package state;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.awt.geom.Point2D;

/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in display width and height.
 * X and Y properties reflect the coordinates of the turtle view's top left corner.
 * @author Hunter Gregory
 * @author David Miron
 */
public class Turtle {

    private double myPaneWidth;
    private double myPaneHeight;

    private IntegerProperty myTurtleID;
    private ObjectProperty<Point2D> myPositionProperty;
    private DoubleProperty myHeadingProperty;
    private BooleanProperty myIsShowingProperty;
    private BooleanProperty myIsActiveProperty;
    private Pen myPen;
    private ObjectProperty<String> myImageProperty; //could use ObjectProperty<TurtleImage> too if there were a TurtleImageManager

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     */
    public Turtle(int turtleID, double pwidth, double pheight, Palette penColor) {
        myPaneWidth = pwidth;
        myPaneHeight = pheight;
        myPen = new Pen(penColor);
        instantiateProperties(turtleID);
        setDefaultState();
    }

    private void instantiateProperties(int turtleID) {
        myTurtleID = new SimpleIntegerProperty(turtleID);
        myPositionProperty = new SimpleObjectProperty<>();
        myHeadingProperty = new SimpleDoubleProperty();
        myIsShowingProperty = new SimpleBooleanProperty();
        myIsActiveProperty = new SimpleBooleanProperty(true);
        myImageProperty = new SimpleObjectProperty<>();
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min)
            return min;
        if (num > max)
            return max;
        return num;
    }

    private void setDefaultState() {
        setPosition(0,0);
        setHeading(0);
        setShowing(true);
    }

    public void setPosition(double x, double y) {
        double newX = getInBoundsNum(x, - myPaneWidth / 2.0, myPaneWidth / 2.0);
        double newY = getInBoundsNum(y, - myPaneHeight / 2.0, myPaneHeight / 2.0);
        myPositionProperty.set(new Point2D.Double(newX, newY));
    }

    public ObjectProperty<String> getImageProperty() {
        return myImageProperty;
    }

    public void setImageProperty(String name) {
        myImageProperty.set(name);
    }

    public void setImageIndex(int index) {
        setImageProperty("Turtle " + index + ".png");
    }

    public double getImageIndex() {
        String shortened = myImageProperty.getValue().substring(myImageProperty.getValue().indexOf(" ") + 1);
        String index = shortened.split(".")[0];
        return Double.parseDouble(index);
    }

    public void setHeading(double heading) {
        myHeadingProperty.set(heading);
    }

    public void setShowing(boolean bool) {
        myIsShowingProperty.set(bool);
    }

    public void eraseLines() {
        myPen.eraseLines();
    }

    public void setActive(boolean active) {
        myIsActiveProperty.set(active);
    }

    public IntegerProperty getTurtleIDProperty() {
        return myTurtleID;
    }

    public ObjectProperty<Point2D> getPositionProperty() {
        return myPositionProperty;
    }

    public DoubleProperty getHeadingProperty() {
        return myHeadingProperty;
    }

    public BooleanProperty getShowingProperty() {
        return myIsShowingProperty;
    }

    public BooleanProperty getActiveProperty() {
        return myIsActiveProperty;
    }

    public Pen getPen() {
        return myPen;
    }

    public int getID() {
        return myTurtleID.get();
    }

    public Point2D getPosition() {
        return myPositionProperty.get();
    }

    public double getX() {
        return myPositionProperty.get().getX();
    }

    public double getY() {
        return myPositionProperty.get().getY();
    }

    public double getHeading() {
        return myHeadingProperty.get();
    }

    public boolean getShowing() {
        return myIsShowingProperty.get();
    }

    public boolean getIsActive() {
        return myIsActiveProperty.get();
    }
}
