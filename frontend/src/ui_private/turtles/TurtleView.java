package ui_private.turtles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import parser_public.TurtleManager;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.awt.geom.Point2D;


/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class TurtleView {
    static final double WIDTH = 20;
    static final double HEIGHT = 20;
    private static final String CSS_TAG = "turtle";

    Node myNode; //must be accessed by subclass

    private int myID;
    private ObservableList myModifiableList;
    private Pen myPen;

    private BooleanProperty myIsShowingProperty = new SimpleBooleanProperty();
    private DoubleProperty myHeadingProperty = new SimpleDoubleProperty();
    private ObjectProperty<Point2D> myPositionProperty = new SimpleObjectProperty<>();
    private Point2D myOldPoint = new Point2D.Double(0, 0);
    private Point2D myNewPoint = new Point2D.Double(0, 0);
    private double myDispXOffset;
    private double myDispYOffset;   //tradeoff: have to wait until both x and y have been updated to draw and updateOnPositionChange turtle
                                    //could have created a Coordinate object, but then front and back end would have to share this class


    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param id
     * @param list
     */
    TurtleView(int id, ObservableList list, double dispX, double dispY) {
        myID = id;
        myDispXOffset = dispX;
        myDispYOffset = dispY;

        myModifiableList = list;
        initializeNode();
        myNode.getStyleClass().add(CSS_TAG);
        myModifiableList.add(myNode);
        myPen = new Pen(myID, myModifiableList);

        addPropertyListeners();
        bindProperties();
        eraseLines(); // a dot of a line is added if not called???????????

        move();
    }

    /**
     * TurtleView class depends on the implementation assigning a nonnull Node to myNode.
     */
    abstract protected void initializeNode();

    /**
     * Removes the turtle and its lines from the scene
     */
    public void removeFromScene() {
        myPen.erase();
        myModifiableList.remove(myNode);
    }

    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    public void setPenColor(Color color) {
        myPen.setPenColor(color);
    }

    private void addPropertyListeners() {
        myPositionProperty.addListener((o, oldVal, newVal) -> updatePosition(oldVal, newVal));
        myHeadingProperty.addListener((o, oldVal, newVal) -> myNode.setRotate(newVal.doubleValue()));
        myIsShowingProperty.addListener((o, oldVal, newVal) -> myNode.setVisible(newVal));
    }

    private void bindProperties() {
        var manager = TurtleManager.getInstance();
        myPositionProperty.bind(manager.getPositionProperty(myID));
        myHeadingProperty.bind(manager.getHeadingProperty(myID));
        myIsShowingProperty.bind(manager.getShowingProperty(myID));
    }

    private void updatePosition(Point2D oldPoint, Point2D newPoint) {
        if (oldPoint == null) {
            myOldPoint = newPoint;
        }
        else {
            myOldPoint = oldPoint;
        }
        myNewPoint = newPoint;
        move();
    }

    private void move() {
        myNode.relocate(myNewPoint.getX() - TurtleView.WIDTH / 2.0 + myDispXOffset,
                myNewPoint.getY() - TurtleView.HEIGHT / 2.0 + myDispYOffset);
        myPen.draw(myOldPoint.getX() + myDispXOffset,
                myOldPoint.getY() + myDispYOffset,
                myNewPoint.getX() + myDispXOffset,
                myNewPoint.getY() + myDispYOffset);
        moveAboveLines();
    }

    /**
     * Call after move if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }

    private void eraseLines() {
        myPen.erase();
    }
}
