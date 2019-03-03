package state_public;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

    public void runTurtleCommand(Consumer<Turtle> func) {
        for (Turtle turtle: myActiveTurtles)
            func.accept(turtle);
    }

}
