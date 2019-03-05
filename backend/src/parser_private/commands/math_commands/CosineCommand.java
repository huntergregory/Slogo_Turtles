package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class CosineCommand extends SingleParamMathCommand {

    public CosineCommand(List<CommandInter> params) {
        super(params);
    }

    public double execute() {
        return Math.cos(Math.toRadians(myExpression.execute()));
    }
}
