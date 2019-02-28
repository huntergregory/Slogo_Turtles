package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class MoveToPositionCommand extends TurtleCommand {

    double myNewX;
    double myNewY;

    MoveToPositionCommand(List<Command> params) {
        super(params);
        if (params.size() != 0) {
            myNewX = params.get(0).execute();
            myNewY = params.get(1).execute();
        }
    }

    private double getCartesianDistance() {
        double oldX = myManager.getX();
        double oldY = myManager.getY();
        double deltaXSquared = Math.pow(myNewX - oldX, 2);
        double deltaYSquared = Math.pow(myNewY - oldY, 2);
        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    @Override
    public double runCommand() {
        System.out.println("new position: " + myNewX + " " + myNewY);
        double distanceTravelled = getCartesianDistance();
        myManager.setPosition(myNewX, - myNewY);
        return distanceTravelled;
    }
}
