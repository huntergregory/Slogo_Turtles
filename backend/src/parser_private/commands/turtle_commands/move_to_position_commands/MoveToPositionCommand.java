package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import state_public.CommandInter;
import state_public.Turtle;

import java.util.List;

public class MoveToPositionCommand extends Command {

    CommandInter myNewX;
    CommandInter myNewY;

    MoveToPositionCommand(List<CommandInter> params) {
        super(params);
        if (params.size() != 0) {
            myNewX = params.get(0);
            myNewY = params.get(1);
        }
    }

    private double getCartesianDistance(Turtle turtle, double newX, double newY) {
        double oldX = turtle.getPosition().getX();
        double oldY = turtle.getPosition().getY();
        double deltaXSquared = Math.pow(newX - oldX, 2);
        double deltaYSquared = Math.pow(newY - oldY, 2);
        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    @Override
    public double runCommand() {
        double newX = myNewX.execute();
        double newY = myNewY.execute();
        System.out.println("new position: " + newX + " " + newY);
        return myStateManager.getTurtleManager().runTurtleCommand((turtle) -> {
            double distanceTravelled = getCartesianDistance(turtle, newX, newY);
            turtle.setPosition(newX, - newY);
            return distanceTravelled;
        });
    }
}
