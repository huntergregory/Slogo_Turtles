package state_public;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TurtleManager {

    public static final String ID_VARNAME = "ID";

    private GlobalVariables myVariables;
    private PaletteManager myPaletteManager;
    private List<Turtle> myTurtles;
    private List<Integer> myPreviousActiveTurtles;
    private Palette myBackgroundColor;

    private double panelWidth;
    private double panelHeight;

    public TurtleManager(GlobalVariables variables, PaletteManager paletteManager) {
        myVariables = variables;
        myPaletteManager = paletteManager;
        myTurtles = new ArrayList<>();
        myBackgroundColor = myPaletteManager.getDefaultBackgroundColor();
    }

    public void setPanelWidthHeight(double width, double height) {
        panelWidth = width;
        panelHeight = height;
    }

    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    public Turtle getTurtle(int id) {
        return myTurtles.stream().filter(turtle -> turtle.getID() == id).findFirst().get();
    }

    public List<Turtle> getActiveTurtles() {
        return myTurtles.stream().filter(turtle -> turtle.getIsActive()).collect(Collectors.toList());
    }

    public void addTurtle() {
        addTurtle(myTurtles.size());
    }

    public void addTurtle(int id) {
        if (myTurtles.stream().anyMatch(turtle -> turtle.getID() == id))
            return;
        myTurtles.add(new Turtle(id, panelWidth, panelHeight, myPaletteManager.getDefaultPenColor()));
    }

    public double runTurtleCommand(Function<Turtle, Double> func) {
        var retvalWrapper = new Object() { double retval = 0; };
        myTurtles.stream().filter(turtle -> turtle.getIsActive())
                          .forEach((turtle) -> {
                              myVariables.setVariable(ID_VARNAME, turtle.getID());
                              retvalWrapper.retval = func.apply(turtle);
                          });
        return retvalWrapper.retval;
    }

    private void saveActiveTurtles() {
        myPreviousActiveTurtles = myTurtles.stream()
                                           .filter(turtle -> turtle.getIsActive())
                                           .map(turtle -> turtle.getID())
                                           .collect(Collectors.toList());
    }

    public List<Integer> getTurtlesWithCondition(Predicate<Turtle> tester) {
        saveActiveTurtles();
        setAllTurtlesInactive();
        List<Integer> validIDs = new ArrayList<>();
        myTurtles.forEach(turtle -> {
            turtle.setActive(true);
            if (tester.test(turtle))
                validIDs.add(turtle.getID());
            turtle.setActive(false);
        });
        return validIDs;
    }

    private void setAllTurtlesInactive() {
        myTurtles.forEach(turtle -> turtle.setActive(false));
    }

    public void setTurtlesActive(List<Integer> ids) {
        saveActiveTurtles();
        setAllTurtlesInactive();
        setIDsActive(ids);
    }

    private void setIDsActive(List<Integer> ids) {
        for (int id: ids) {
            for (Turtle turtle : myTurtles) {
                if (turtle.getID() == id) {
                    turtle.setActive(true);
                    break;
                }
            }
            addTurtle(id);
        }
    }


    public void revertActiveTurtles() {
        setIDsActive(myPreviousActiveTurtles);
    }

    public Palette getBackgroundColor() {
        return myBackgroundColor;
    }
}
