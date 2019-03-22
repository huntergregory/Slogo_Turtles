package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state.ICommand;
import state.TurtleManager;

import java.util.List;

/**
 * @author David Miron
 */
public class IDCommand extends Command {

    public IDCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the ID of the current active turtle
     * @return The ID of the current active turtle
     */
    @Override
    public double execute() {
        return myStateManager.getVariables().getVariable(TurtleManager.ID_VARNAME);
    }

}
