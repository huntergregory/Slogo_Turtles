package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class TangentCommand extends SingleParamMathCommand {

    public TangentCommand(List<CommandInter> params) {
        super(params);
    }

    public double runCommand() {
        return Math.tan(Math.toRadians(myExpression.execute()));
    }
}
