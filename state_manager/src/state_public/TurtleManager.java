package state_public;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    public static final String ID_VARNAME = "ID";

    private GlobalVariables myVariables;
    private PaletteManager myPaletteManager;
    private List<Turtle> myTurtles;

    private double panelWidth;
    private double panelHeight;

    public TurtleManager(GlobalVariables variables, PaletteManager paletteManager) {
        myVariables = variables;
        myPaletteManager = paletteManager;
        myTurtles = new ArrayList<>();
    }

    public void setPanelWidthHeight(double width, double height) {
        panelWidth = width;
        panelHeight = height;
    }

    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    public List<Turtle> getActiveTurtles() {
        return getActiveTurtles();
    }

    public void addTurtle() {
        myTurtles.add(new Turtle(myTurtles.size(), panelWidth, panelHeight, myPaletteManager.getDefaultPenColor()));
    }

    public double runTurtleCommand(ReturnConsumer<Double, Turtle> func) {
        var retvalWrapper = new Object() { double retval = 0; };
        myTurtles.stream().filter(turtle -> turtle.getIsActive())
                          .forEach((turtle) -> {
                              myVariables.setVariable(ID_VARNAME, turtle.getID());
                              retvalWrapper.retval = func.accept(turtle);
                          });
        return retvalWrapper.retval;
    }

    public void setTurtleActive(int id) {
        for (Turtle turtle: myTurtles) {
            if (turtle.getID() == id) {
                turtle.setActive(true);
                return;
            }
        }
        addTurtle();
    }
}
