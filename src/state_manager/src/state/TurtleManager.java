package state;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class to manage turtles and run commands on them
 * @author David Miron
 */
public class TurtleManager {

    public static final String ID_VARNAME = "ID";

    private GlobalVariables myVariables;
    private PaletteManager myPaletteManager;
    private List<Turtle> myTurtles;
    private List<Integer> myPreviousActiveTurtles;
    private Palette myBackgroundColor;
    private ObjectProperty<Turtle> myNewTurtleProperty;
    private BooleanProperty myStampProperty;
    private BooleanProperty myClearStampsProperty;
    private boolean myHasStamps;

    private double panelWidth;
    private double panelHeight;

    public TurtleManager(GlobalVariables variables, PaletteManager paletteManager) {
        myVariables = variables;
        myPaletteManager = paletteManager;
        myTurtles = new ArrayList<>();
        myBackgroundColor = myPaletteManager.getDefaultBackgroundColor();
        myNewTurtleProperty = new SimpleObjectProperty<>();
        myStampProperty = new SimpleBooleanProperty(false);
        myClearStampsProperty = new SimpleBooleanProperty(false);
        myHasStamps = false;
    }

    /**
     * Set the panel dimensions
     * @param width The width
     * @param height The height
     */
    public void setPanelWidthHeight(double width, double height) {
        panelWidth = width;
        panelHeight = height;
    }

    /**
     * Get a list of turtles
     * @return The turtles
     */
    public List<Turtle> getTurtles() {
        return myTurtles;
    }

    /**
     * Get a specific turtle
     * @param id The id of the turtle
     * @return The turtle with the given ID
     */
    public Turtle getTurtle(int id) {
        return myTurtles.stream().filter(turtle -> turtle.getID() == id).findFirst().get();
    }

    /**
     * Get the new turtle property
     * @return The new turtle property
     */
    public ObjectProperty<Turtle> getNewTurtleProperty() {
        return myNewTurtleProperty;
    }

    /**
     * Get the active turtles
     * @return A list of active turtles
     */
    public List<Turtle> getActiveTurtles() {
        return myTurtles.stream().filter(turtle -> turtle.getIsActive()).collect(Collectors.toList());
    }

    /**
     * Add a new turtle
     * @param id The id of the new turtle
     */
    public void addTurtle(int id) {
        if (myTurtles.stream().anyMatch(turtle -> turtle.getID() == id))
            return;
        Turtle newTurtle = new Turtle(id, panelWidth, panelHeight, myPaletteManager.getDefaultPenColor());
        myTurtles.add(newTurtle);
        myNewTurtleProperty.set(newTurtle);
    }

    /**
     * Run a turtle command for each active turtle, setting the ID to be valid for all subcommands
     * @param func The function to run on each turtle
     * @return The return value of the last run command
     */
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

    /**
     * Get a list of ids of turtles who satisfy some condition
     * @param tester The function to determine if a turtle satisfies the condition
     * @return A list of IDs
     */
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

    /**
     * Set the turtles with ids given to be active
     * @param ids The IDs
     */
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


    /**
     * Reset the active turtles to the previous state
     */
    public void revertActiveTurtles() {
        setAllTurtlesInactive();
        setIDsActive(myPreviousActiveTurtles);
    }

    /**
     * Get the background color
     * @return The background color
     */
    public Palette getBackgroundColor() {
        return myBackgroundColor;
    }

    /**
     * Set the image index for all turtles
     * @param index The index of the image
     */
    public void setImageIndex(int index) {
        if (index > 0 && index <= 3)
            for (Turtle turtle : myTurtles) {
                turtle.setImageProperty("Turtle " + index + ".png");
            }
    }

    /**
     * Get the index of the current image
     * @return The index
     */
    public double getImageIndex() {
        try {
            String shortened = myTurtles.get(0).getImageProperty().getValue().substring(myTurtles.get(0).getImageProperty().getValue().indexOf(" ") + 1);
            String index = shortened.substring(0, shortened.indexOf("."));
            return Double.parseDouble(index);
        } catch (NullPointerException e) {
            return 1;
        }
    }

    public double addStamp() {
        myStampProperty.setValue(true);
        myStampProperty.setValue(false);
        myHasStamps = true;
        return this.getImageIndex();
    }

    public double clearStamps() {
        myClearStampsProperty.setValue(true);
        myClearStampsProperty.setValue(false);
        int rtn = myHasStamps ? 1 : 0;
        myHasStamps = false;
        return rtn;
    }

    public BooleanProperty getStampProperty() {
        return myStampProperty;
    }

    public BooleanProperty getClearStampsProperty() {
        return myClearStampsProperty;
    }
}
