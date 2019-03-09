package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 * @author Hunter Gregory
 */
public abstract class RotateAngleCommand extends TurtleCommand {

    private ICommand myAngle;
    private boolean myGoingRight;

    RotateAngleCommand(List<ICommand> params, boolean goingRight) {
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
    public double execute() {
        double angle = getAngle();
        return runTurtleCommand((turtle) -> {
            turtle.setHeading(angle + turtle.getHeading());
            return Math.abs(angle);
        });
    }
}
