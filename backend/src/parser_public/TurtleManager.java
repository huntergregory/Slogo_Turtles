package parser_public;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import parser_private.Turtle;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Singleton that manages all turtles in the backend. Initialize method must be called first to set the
 * display and turtle width and height.
 */
public class TurtleManager {
    private static TurtleManager instance;

    private ArrayList<Turtle> myTurtles;

    private TurtleManager() {
        myTurtles = new ArrayList<>();
    }

    public static TurtleManager getInstance() {
        if (instance == null) {
            instance = new TurtleManager();
        }
        return instance;
    }

    public void setPosition(double x, double y) {
        myTurtles.get(0).setPosition(x,y);
    }

    public void setHeading(double heading) {
        myTurtles.get(0).setHeading(heading);
    }

    public void setPenDown(boolean bool) {
        myTurtles.get(0).setPenDown(bool);
    }

    public void setIsShowing(boolean bool) {
        myTurtles.get(0).setShowing(bool);
    }

    public void eraseLines() {
        myTurtles.get(0).eraseLines();
    }

    public double getX() {
        return getPositionProperty(0).getValue().getX();
    }

    public double getY() {
        return getPositionProperty(0).getValue().getY();
    }

    public double getHeading() {
        return getHeadingProperty(0).getValue();
    }

    public boolean getIsShowing() {
        return getShowingProperty(0).getValue();
    }

    public boolean getPenDown() {
        return getDownProperty(0).getValue();
    }

    public void initialize(double paneWidth, double paneHeight) {
        addTurtle(paneWidth, paneHeight);
    }

    public void addTurtle(double paneWidth, double paneHeight) {
        int id = myTurtles.size();
        myTurtles.add(new Turtle(id, paneWidth, paneHeight));
    }

    public void removeTurtle() {
        removeTurtle(myTurtles.size() - 1);
    }

    public void removeTurtle(int id) {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        myTurtles.remove(id);
    }

    public ObjectProperty<Point2D> getPositionProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getPositionProperty();
    }

    public DoubleProperty getHeadingProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getHeadingProperty();
    }

    public BooleanProperty getDownProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getDownProperty();
    }

    public BooleanProperty getShowingProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getShowingProperty();
    }

    public BooleanProperty getEraseProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getEraseProperty();
    }

    private boolean idOutOfBounds(int id) {
        return id >= myTurtles.size() || id < 0;
    }
}
