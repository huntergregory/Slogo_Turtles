package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class PowerCommand extends TwoParamMathCommand {

    public PowerCommand(List<CommandInter> params) {
        super(params);
    }

    public double runCommand() {
        return Math.pow(myExpression1.execute(), myExpression2.execute());
    }
}
