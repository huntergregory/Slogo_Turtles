package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class TurtlesCommand extends Command {

    public TurtlesCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return myStateManager.getTurtleManager().getTurtles().size();
    }

}