package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;

import java.util.List;

public class RotateAngleCommand extends TurtleCommand {

    private Command myAngle;
    private boolean myGoingRight;

    RotateAngleCommand(List<Command> params, boolean goingRight) {
        super(params);
        myAngle = params.get(0);
        myGoingRight = goingRight;
    }

    @Override
    public double runCommand() {
        double angle = myAngle.execute();
        if (!myGoingRight) {
            angle *= -1;
        }
        myManager.setHeading(angle + myManager.getHeading());
        System.out.println("Rotating " + angle);
        return Math.abs(angle);
    }
}
