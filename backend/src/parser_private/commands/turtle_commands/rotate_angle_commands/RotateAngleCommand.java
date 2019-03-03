package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public abstract class RotateAngleCommand extends TurtleCommand {

    private CommandInter myAngle;
    private boolean myGoingRight;

    RotateAngleCommand(List<CommandInter> params, boolean goingRight) {
        super(params);
        myAngle = params.get(0);
        myGoingRight = goingRight;
    }

    private double getAngle() {
        double angle = myAngle.execute();
        if (!myGoingRight) {
            angle *= -1;
        }
        return angle;
    }

    @Override
    public double runCommand() {
        double angle = getAngle();
        System.out.println("Rotating " + angle);
        return runTurtleCommand((turtle) -> {
            turtle.setHeading(angle + turtle.getHeading());
            return Math.abs(angle);
        });
    }
}
