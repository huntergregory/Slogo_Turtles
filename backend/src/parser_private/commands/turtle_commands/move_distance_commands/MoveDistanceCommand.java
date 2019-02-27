package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;
import parser_public.TurtleManager;

import java.util.List;

public abstract class MoveDistanceCommand extends Command {
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
        var manager = TurtleManager.getInstance();
        double heading = Math.toRadians(manager.getHeading());
        double newX = manager.getX() + Math.sin(heading) * distance;
        double newY = manager.getY() - Math.cos(heading) * distance;
        manager.setPosition(newX, newY);
    }
}
