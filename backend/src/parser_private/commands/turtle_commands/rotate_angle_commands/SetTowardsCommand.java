package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class SetTowardsCommand extends TurtleCommand {

    private Command myX;
    private Command myY;

    public SetTowardsCommand(List<Command> params) {
        super(params);
        myX = params.get(0);
        myY = params.get(1);
    }

    private double getNewHeading() {
        double x = myX.execute();
        double y = myY.execute();
        double deltaX = x - myManager.getX();
        double deltaY = -(y - myManager.getY());
        if (deltaX == 0 && deltaY == 0)
            return myManager.getHeading();

        double newHeading;
        if (deltaY == 0)
            newHeading = (deltaX > 0) ? 90 : -90; // OR 270
        else {
            double upLeftOrUpRightHeading = Math.toDegrees(Math.atan(deltaX / deltaY));
            System.out.println("proposed heading: " + upLeftOrUpRightHeading);
            newHeading = (deltaX >= 0 && deltaY > 0 || deltaX <= 0 && deltaY > 0) ? upLeftOrUpRightHeading : upLeftOrUpRightHeading + 180;
            System.out.println("set heading: " + newHeading);
        }
        return newHeading;
    }

    @Override
    public double runCommand() {
        double newHeading = getNewHeading();
        double oldHeading = myManager.getHeading();
        myManager.setHeading(newHeading);
        return newHeading - oldHeading;
    }
}
