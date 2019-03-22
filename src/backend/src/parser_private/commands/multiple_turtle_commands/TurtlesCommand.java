package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class TurtlesCommand extends Command {

    public TurtlesCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the number of turtles
     * @return The number of turtles
     */
    @Override
    public double execute() {
        return myStateManager.getTurtleManager().getTurtles().size();
    }

}
