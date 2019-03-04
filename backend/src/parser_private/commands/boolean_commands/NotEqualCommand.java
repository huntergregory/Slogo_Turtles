package parser_private.commands.boolean_commands;

import state_public.CommandInter;

import java.util.List;

public class NotEqualCommand extends TwoParamBoolCommand {

    public NotEqualCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myExpression1.execute() != myExpression2.execute()) ? 1 : 0;
    }
}
