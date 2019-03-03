package parser_private.commands.turtle_commands;

import parser_private.Command;
import state_public.CommandInter;
import state_public.Turtle;

import java.util.List;

public abstract class TurtleCommand extends Command {

    public TurtleCommand(List<CommandInter> params) {
        super(params);
    }

}
