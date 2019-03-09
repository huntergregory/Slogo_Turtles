package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 * @author Hunter Gregory
 */
public class SetHeadingCommand extends TurtleCommand {

    private ICommand myNewHeading;

    public SetHeadingCommand (List<ICommand> params) {
        super(params);
        myNewHeading = params.get(0);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            double newHeading = myNewHeading.execute();
            double oldHeading = turtle.getHeading();
            turtle.setHeading(newHeading);
            return Math.abs(newHeading - oldHeading);
        });
    }
}
