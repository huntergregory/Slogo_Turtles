package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class SumCommand extends MultiParamMathCommand {

    public SumCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double execute() {
        double rtn = 0;
        for (CommandInter exp : myExpressions) {
            rtn += exp.execute();
        }
        return rtn;
    }
}
