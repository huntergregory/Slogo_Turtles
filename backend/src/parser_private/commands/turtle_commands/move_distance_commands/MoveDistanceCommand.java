package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import parser_public.TurtleManager;

import java.util.List;

public abstract class MoveDistanceCommand extends TurtleCommand {
    private Command myDistance;
    private boolean myGoingForward;

    public MoveDistanceCommand(List<Command> params, boolean goingForward) {
        super(params);
        this.myDistance = params.get(0);
        myGoingForward = goingForward;
    }

    @Override
    public double runCommand() {
        double distance = myDistance.execute();
        if (!myGoingForward)
            distance *= -1;
        move(distance);
        return Math.abs(distance);
    }

    private void move(double distance) {
        double heading = Math.toRadians(myManager.getHeading());
        double newX = myManager.getX() + Math.sin(heading) * distance;
        double newY = myManager.getY() - Math.cos(heading) * distance;
        myManager.setPosition(newX, newY);
    }
}
