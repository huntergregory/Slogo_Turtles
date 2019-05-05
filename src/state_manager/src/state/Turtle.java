package state;

import javafx.beans.property.*;

import java.awt.geom.Point2D;

/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in display width and height.
 * X and Y properties reflect the coordinates of the turtle view's top left corner.
 * @author Hunter Gregory
 * @author David Miron
 */
public class Turtle {
    private static final double DEFAULT_ANIMATION_DURATION = 1;

    private double myPaneWidth;
    private double myPaneHeight;

    private IntegerProperty myTurtleID;
    private ObjectProperty<Point2D> myPositionProperty;
    private DoubleProperty myHeadingProperty;
    private BooleanProperty myIsShowingProperty;
    private BooleanProperty myIsActiveProperty;
    private Pen myPen;
    private String myImage;
    private ObjectProperty<String> myImageProperty; //could use ObjectProperty<TurtleImage> too if there were a TurtleImageManager
    private DoubleProperty myAnimationDurationProperty;

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
        myAnimationDurationProperty = new SimpleDoubleProperty();
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
        setAnimationDuration(DEFAULT_ANIMATION_DURATION);
    }

    /**
     * Set the position of a turtle
     * @param x The x
     * @param y The y
     */
    public void setPosition(double x, double y) {
        double newX = getInBoundsNum(x, - myPaneWidth / 2.0, myPaneWidth / 2.0);
        double newY = getInBoundsNum(y, - myPaneHeight / 2.0, myPaneHeight / 2.0);
        myPositionProperty.set(new Point2D.Double(newX, newY));
    }

    /**
     * Get the image property
     * @return The image property
     */
    public ObjectProperty<String> getImageProperty() {
        return myImageProperty;
    }

    public String getImage() {
        return myImage;
    }

    /**
     * Set the image
     * @param name The image
     */
    public void setImageProperty(String name) {
        myImageProperty.set(name);
        myImage = name;
    }

    /**
     * Set the heading
     * @param heading The heading
     */
    public void setHeading(double heading) {
        myHeadingProperty.set(heading);
    }

    /**
     * Set if the turtle is showing
     * @param bool True if the turtle is showing
     */
    public void setShowing(boolean bool) {
        myIsShowingProperty.set(bool);
    }

    /**
     * Erase lines
     */
    public void eraseLines() {
        myPen.eraseLines();
    }

    /**
     * Set if the current turtle is active
     * @param active True to set active
     */
    public void setActive(boolean active) {
        myIsActiveProperty.set(active);
    }

    /**
     * Get the turtle ID property
     * @return The turtle ID property
     */
    public IntegerProperty getTurtleIDProperty() {
        return myTurtleID;
    }

    /**
     *  Get the position property
     * @return The position property
     */
    public ObjectProperty<Point2D> getPositionProperty() {
        return myPositionProperty;
    }

    /**
     * Get the heading property
     * @return The heading property
     */
    public DoubleProperty getHeadingProperty() {
        return myHeadingProperty;
    }

    /**
     * Get the showing property
     * @return The showing property
     */
    public BooleanProperty getShowingProperty() {
        return myIsShowingProperty;
    }

    /**
     * Get the active property
     * @return The active property
     */
    public BooleanProperty getActiveProperty() {
        return myIsActiveProperty;
    }

    /**
     * Get the current pen
     * @return The current pen
     */
    public Pen getPen() {
        return myPen;
    }

    /**
     * Get the id of the turtle
     * @return The id of the turtle
     */
    public int getID() {
        return myTurtleID.get();
    }

    /**
     * Get the position of the turtle
     * @return The position of the turtle
     */
    public Point2D getPosition() {
        return myPositionProperty.get();
    }

    /**
     * Get the current X of the turtle
     * @return The current X of the turtle
     */
    public double getX() {
        return myPositionProperty.get().getX();
    }

    /**
     * Get the current Y of the turtle
     * @return The current Y of the turtle
     */
    public double getY() {
        return myPositionProperty.get().getY();
    }

    /**
     * Get the heading of the turtle
     * @return The heading of the turtle
     */
    public double getHeading() {
        return myHeadingProperty.get();
    }

    /**
     * Get if the turtle is showing
     * @return True if the turtle is showing, false otherwise
     */
    public boolean getShowing() {
        return myIsShowingProperty.get();
    }

    /**
     * Get if the turtle is active
     * @return True if the turtle is active
     */
    public boolean getIsActive() {
        return myIsActiveProperty.get();
    }

    /**
     * Set the animation duration
     * @param duration The duration in seconds
     */
    public void setAnimationDuration(double duration) {
        myAnimationDurationProperty.set(duration);
    }

    /**
     * Get the animation duration property
     * @return The animation duration property
     */
    public DoubleProperty getAnimationDurationProperty() {
        return myAnimationDurationProperty;
    }
}
