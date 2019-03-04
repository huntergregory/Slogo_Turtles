package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public abstract class MoveDistanceCommand extends TurtleCommand {

    private CommandInter myDistance;
    private boolean myGoingForward;

    MoveDistanceCommand(List<CommandInter> params, boolean goingForward) {
        super(params);
        this.myDistance = params.get(0);
        myGoingForward = goingForward;
    }

    @Override
    public double runCommand() {
        double distance = myDistance.execute();
        if (!myGoingForward) {
            distance *= -1;
        }
        move(distance);
        return Math.abs(distance);
    }

    private void move(double distance) {
        System.out.println("Moving forward with distance " + distance);
        runTurtleCommand((turtle) -> {
            double heading = Math.toRadians(turtle.getHeading());
            double newX = turtle.getPosition().getX() + Math.sin(heading) * distance;
            double newY = turtle.getPosition().getY() - Math.cos(heading) * distance;
            turtle.setPosition(newX, newY);
            return distance;
        });

    }
}
