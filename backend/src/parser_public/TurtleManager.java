package parser_public;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import parser_private.Turtle;

import java.util.ArrayList;

/**
 * Singleton that manages all turtles in the backend. Initialize method must be called first to set the
 * display and turtle width and height.
 */
public class TurtleManager {
    private static TurtleManager instance;

    private ArrayList<Turtle> myTurtles;
    private double myDisplayWidth;
    private double myDisplayHeight;
    private double myTurtleWidth;
    private double myTurtleHeight;

    private TurtleManager() {
        myTurtles = new ArrayList<>();
    }

    public static TurtleManager getInstance() {
        if (instance == null) {
            instance = new TurtleManager();
        }
        return instance;
    }

    public void initialize(double displayWidth, double displayHeight, double turtleWidth, double turtleHeight) {
        myDisplayWidth = displayWidth;
        myDisplayHeight = displayHeight;
        myTurtleWidth = turtleWidth;
        myTurtleHeight = turtleHeight;
        addTurtle();
    }

    public void addTurtle() {
        int id = myTurtles.size();
        myTurtles.add(new Turtle(id, myDisplayWidth, myDisplayHeight, myTurtleWidth, myTurtleHeight));
    }

    public void removeTurtle() {
        removeTurtle(myTurtles.size() - 1);
    }

    public void removeTurtle(int id) {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        myTurtles.remove(id);
    }

    public DoubleProperty getXProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getXProperty(id);
    }

    public DoubleProperty getYProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getYProperty(id);
    }

    public DoubleProperty getHeadingProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getHeadingProperty(id);
    }

    public BooleanProperty getDownProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getDownProperty(id);
    }

    public BooleanProperty getShowingProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getShowingProperty(id);
    }

    public BooleanProperty getEraseProperty(int id) throws NoTurtleException {
        if (idOutOfBounds(id))
            throw new NoTurtleException();
        return myTurtles.get(id).getEraseProperty(id);
    }

    private boolean idOutOfBounds(int id) {
        return id >= myTurtles.size() || id < 0;
    }
}
