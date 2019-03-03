package state_public;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private List<Turtle> myTurtles;
    private List<Turtle> myActiveTurtles;
    private GlobalVariables myVariables;

    public TurtleManager(GlobalVariables variables) {
        myTurtles = new ArrayList<>();
        myActiveTurtles = new ArrayList<>();
        myVariables = variables;
    }

    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    public List<Turtle> getActiveTurtles() {
        return getActiveTurtles();
    }

    public double runTurtleCommand(ReturnConsumer<Double, Turtle> func) {
        double retval = 0;
        for (Turtle turtle: myActiveTurtles) {
            myVariables.setVariable("ID", turtle.getID());
            retval = func.accept(turtle);
        }
        return retval;
    }

}
