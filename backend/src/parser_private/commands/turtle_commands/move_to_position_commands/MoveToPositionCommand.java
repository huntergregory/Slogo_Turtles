package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class MoveToPositionCommand extends TurtleCommand {

    Command myNewX;
    Command myNewY;

    MoveToPositionCommand(List<Command> params) {
        super(params);
        if (params.size() != 0) {
            myNewX = params.get(0);
            myNewY = params.get(1);
        }
    }

    private double getCartesianDistance(double newX, double newY) {
        double oldX = myManager.getX();
        double oldY = myManager.getY();
        double deltaXSquared = Math.pow(newX - oldX, 2);
        double deltaYSquared = Math.pow(newY - oldY, 2);
        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    @Override
    public double runCommand() {
        double newX = myNewX.execute();
        double newY = myNewY.execute();
        System.out.println("new position: " + newX + " " + newY);
        double distanceTravelled = getCartesianDistance(newX, newY);
        myManager.setPosition(newX, - newY);
        return distanceTravelled;
    }
}
