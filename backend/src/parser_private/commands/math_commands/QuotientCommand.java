package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class QuotientCommand extends TwoParamMathCommand {

    public QuotientCommand(List<CommandInter> params) {
        super(params);
    }
    @Override
    public double runCommand() {
        return myExpression1.execute() / myExpression2.execute();
    }
}
