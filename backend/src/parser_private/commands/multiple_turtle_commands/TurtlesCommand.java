package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class TurtlesCommand extends Command {

    public TurtlesCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return myStateManager.getTurtleManager().getTurtles().size();
    }

}
