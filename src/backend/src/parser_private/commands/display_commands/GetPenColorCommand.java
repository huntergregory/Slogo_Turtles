package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class GetPenColorCommand extends TurtleCommand {

    public GetPenColorCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the current pen color
     * @return The index of the current pen color
     */
    public double execute() {
        return runTurtleCommand(turtle -> (double)turtle.getPen().getColor().getId());
    }
}
