package ui_private.features.scrollable_windows;

import state_public.StateManager;
import state_public.Turtle;
import java.awt.Point;

import java.awt.*;
import java.awt.geom.Point2D;

public class TurtleStateWindow extends ScrollableWindow {
    private static final String TITLE = "Turtle State";

    public TurtleStateWindow(StateManager manager) {
        super(manager);
        getTurtleState();
    }

    @Override
    protected String getLabelText() {
        return TITLE;
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
            System.out.println(currentTurtleState);
            addText(currentTurtleState);
        }
    }

    @Override
    public void refreshWindow() {
        getTurtleState();
    }

}