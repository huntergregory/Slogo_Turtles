package parser_private.commands.turtle_commands.set_pen_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 * @author David Miron
 */
public class PenDownCommand extends TurtleCommand {

    public PenDownCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            turtle.getPen().setIsDown(true);
            return 1.0;
        });

    }
}
