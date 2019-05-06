package parser_private.commands.display_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

public class ClearStampsCommand extends Command {

    public ClearStampsCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return myStateManager.getTurtleManager().clearStamps();
    }
}
