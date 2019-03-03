package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import state_public.CommandInter;
import state_public.TurtleManager;

import java.util.List;

public class IDCommand extends Command {

    public IDCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return getVariable(TurtleManager.ID_VARNAME);
    }

}
