package ui_private.features.scrollable_windows;

import state.StateManager;
import state.Turtle;
/**
 * This class is used to view the states of all active Turtles
 * @author Carter Gay
 */
public class TurtleStateWindow extends ScrollableWindow {
    private static final String TITLE = "Turtle State";

    /**
     * Creates binding between TurtleStateWindow and StateManager
     * @param manager
     */
    public TurtleStateWindow(StateManager manager) {
        super(manager);
        getTurtleState();
    }

    // current state of a turtle (i.e., its ID, position, heading) and its pen (i.e., up/down, color, thickness)
    protected void getTurtleState() {
        clearText();
        for(Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            String currentTurtleState = "";
            double x = turtle.getX();
            double y = turtle.getY();
            currentTurtleState += ("ID: " + turtle.getID()  + "\n");
            currentTurtleState += ("Postion: (" + x + "," + y + ")" + "\n");
            currentTurtleState += ("Heading: " + turtle.getHeading() + "\n");
            currentTurtleState += ("Pen Down: " + turtle.getPen().getIsDown() + "\n");
            currentTurtleState += ("Pen Color: " + turtle.getPen().getColor() + "\n");
            currentTurtleState += ("Pen Thickness: " + turtle.getPen().getThicknessProperty() + "\n");
            addText(currentTurtleState);
        }
    }

    /**
     * Update the turtle state window
     */
    @Override
    public void refreshWindow() {
        getTurtleState();
    }

}