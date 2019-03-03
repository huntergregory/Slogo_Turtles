package state_public;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private GlobalVariables myVariables;
    private PaletteManager myPaletteManager;
    private List<Turtle> myTurtles;
    private List<Turtle> myActiveTurtles;

    public TurtleManager(GlobalVariables variables, PaletteManager paletteManager) {
        myVariables = variables;
        myPaletteManager = paletteManager;
        myTurtles = new ArrayList<>();
        myActiveTurtles = new ArrayList<>();
    }

    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    public List<Turtle> getActiveTurtles() {
        return getActiveTurtles();
    }

    public void addTurtle(double panelWidth, double panelHeight) {
        myTurtles.add(new Turtle(myTurtles.size(), panelWidth, panelHeight, myPaletteManager.getDefaultPenColor()));
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
