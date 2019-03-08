package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state.ICommand;
import state.TurtleManager;

import java.util.List;

public class IDCommand extends Command {

    public IDCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return myStateManager.getVariables().getVariable(TurtleManager.ID_VARNAME);
    }

}
