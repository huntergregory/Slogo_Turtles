package parser_private.commands.turtle_commands;

import state.ICommand;
import state.Turtle;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Stamp extends TurtleCommand {

    private static int currID = -10000;

    public Stamp(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        System.out.println("Stamp");

        //for (int i = 0; i < 4; i++) {
        //    moveAndTurn(turtle);
        //}
        runTurtleCommand(turtle -> {
            List<Integer> ids = new ArrayList<>();
            ids.add(currID);
            List<Integer> sameID = new ArrayList<>();
            sameID.add(turtle.getID());
            myStateManager.getTurtleManager().setTurtlesActive(ids);
            myStateManager.getTurtleManager().setTurtlesActive(sameID);
            return 0.0;
        });
        currID++;
        return 0;
    }

    private void moveAndTurn(Turtle turtle) {
        Point2D pos = turtle.getPosition();
        turtle.setPosition(pos.getX() + Math.sin(turtle.getHeading()) * 20,
                pos.getY() - Math.cos(turtle.getHeading()) * 20);
        turtle.setHeading(turtle.getHeading() + 1.57);
    }
}
