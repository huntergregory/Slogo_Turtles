package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class DifferenceCommand extends TwoParamMathCommand {

    public DifferenceCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double execute() {
        return myExpression1.execute() - myExpression2.execute();
    }
}
