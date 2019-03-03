package state_public;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private List<Turtle> myTurtles;
    private List<Turtle> myActiveTurtles;

    public TurtleManager() {
        myTurtles = new ArrayList<>();
        myActiveTurtles = new ArrayList<>();
    }

    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    public List<Turtle> getActiveTurtles() {
        return getActiveTurtles();
    }

}
