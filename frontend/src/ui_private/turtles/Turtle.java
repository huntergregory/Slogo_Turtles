package ui_private.turtles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import parser_public.TurtleManager;
import ui_private.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class Turtle {
    public static final double WIDTH = 20;
    public static final double HEIGHT = 20;
    public static final String CSS_TAG = "turtle";

    protected Node myNode; //must be accessed by subclass

    private int myID;
    private ObservableList myModifiableList;
    private Pen myPen;

    private BooleanProperty myIsShowingProperty = new SimpleBooleanProperty();
    private DoubleProperty myHeadingProperty = new SimpleDoubleProperty();
    private DoubleProperty myXProperty = new SimpleDoubleProperty();
    private DoubleProperty myYProperty = new SimpleDoubleProperty();
    private double myOldX = 0;
    private double myOldY = 0;
    private double myNewX = 0;
    private double myNewY = 0;
    private double myDispXOffset;
    private double myDispYOffset;   //tradeoff: have to wait until both x and y have been updated to draw and updateOnPositionChange turtle
                                    //could have created a Coordinate object, but then front and back end would have to share this class


    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param id
     * @param list
     */
    public Turtle(int id, ObservableList list, double dispX, double dispY) {
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
     * Turtle class depends on the implementation assigning a nonnull Node to myNode.
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

    private void addPropertyListeners() {
        myXProperty.addListener((o, oldVal, newVal) -> { updateX(oldVal.doubleValue(), newVal.doubleValue()); System.out.println("updating x " + oldVal + " " + newVal);  });
        myYProperty.addListener((o, oldVal, newVal) -> { updateY(oldVal.doubleValue(), newVal.doubleValue()); System.out.println("updating y" + oldVal + " " + newVal);  });
        myHeadingProperty.addListener((o, oldVal, newVal) -> { myNode.setRotate(newVal.doubleValue()); System.out.println("rotating");  } );
        myIsShowingProperty.addListener((o, oldVal, newVal) -> { myNode.setVisible(newVal); System.out.println("hiding or showing");  } );
    }

    private void bindProperties() {
        var manager = TurtleManager.getInstance();
        myXProperty.bind(manager.getXProperty(myID));
        myYProperty.bind(manager.getYProperty(myID));
        myHeadingProperty.bind(manager.getHeadingProperty(myID));
        myIsShowingProperty.bind(manager.getShowingProperty(myID));
    }

    private void updateX(double oldVal, double newVal) {
        myOldX = oldVal;
        myNewX = newVal;
    }

    private void updateY(double oldVal, double newVal) {
        myOldY = oldVal;
        myNewY = newVal;
        move();
    }

    private void move() {
        if (!didNotMove()) {
            myNode.relocate(myNewX - Turtle.WIDTH / 2.0 + myDispXOffset, myNewY - Turtle.HEIGHT / 2.0 + myDispYOffset);
            myPen.draw(myOldX + Turtle.WIDTH / 2.0,
                    myOldY + Turtle.HEIGHT / 2.0,
                    myNewX + Turtle.WIDTH / 2.0,
                    myNewY + Turtle.HEIGHT / 2.0);
            moveAboveLines();
        }
    }

    private boolean didNotMove() {
        return myOldX == myNewX && myOldY == myNewY;
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
