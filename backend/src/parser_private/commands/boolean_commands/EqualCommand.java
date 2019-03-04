package parser_private.commands.boolean_commands;

import state_public.CommandInter;

import java.util.List;

public class EqualCommand extends TwoParamBoolCommand {

    public EqualCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double execute() {
        return (myExpression1.execute() == myExpression2.execute()) ? 1 : 0;
    }
}
