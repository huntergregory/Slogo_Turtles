package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;
import state.Turtle;

import java.util.List;

public class SetTowardsCommand extends TurtleCommand {

    private ICommand myX;
    private ICommand myY;

    public SetTowardsCommand(List<ICommand> params) {
        super(params);
        myX = params.get(0);
        myY = params.get(1);
    }

    private double getNewHeading(Turtle turtle, double xTarget, double yTarget) {

        double deltaX = xTarget - turtle.getPosition().getX();
        double deltaY = -(yTarget - turtle.getPosition().getY());
        if (deltaX == 0 && deltaY == 0)
            return turtle.getHeading();

        double newHeading;
        if (deltaY == 0)
            newHeading = (deltaX > 0) ? 90 : -90; // OR 270
        else {
            double upLeftOrUpRightHeading = Math.toDegrees(Math.atan(deltaX / deltaY));
            newHeading = (deltaX >= 0 && deltaY > 0 || deltaX <= 0 && deltaY > 0) ? upLeftOrUpRightHeading : upLeftOrUpRightHeading + 180;
        }
        return newHeading;
    }

    @Override
    public double execute() {
        double xTarget = myX.execute();
        double yTarget = - myY.execute();

        return runTurtleCommand((turtle) -> {
            double newHeading = getNewHeading(turtle, xTarget, yTarget);
            double oldHeading = turtle.getHeading();
            turtle.setHeading(newHeading);
            return newHeading - oldHeading;
        });
    }
}
