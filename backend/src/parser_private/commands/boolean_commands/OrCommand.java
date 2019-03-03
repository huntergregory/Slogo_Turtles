package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class OrCommand extends TwoParamBoolCommand {

    public OrCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myExpression1.execute() != 0 || myExpression2.execute() != 0) ? 1 : 0;
    }
}
