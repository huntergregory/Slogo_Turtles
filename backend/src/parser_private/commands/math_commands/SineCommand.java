package parser_private.commands.math_commands;

import state_public.CommandInter;

import java.util.List;

public class SineCommand extends SingleParamMathCommand {

    public SineCommand(List<CommandInter> params) {
        super(params);
    }

    public double runCommand() {
        return Math.sin(Math.toRadians(myExpression.execute()));
    }
}
