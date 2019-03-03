package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class ArcTangentCommand extends SingleParamMathCommand {

    public ArcTangentCommand(List<CommandInter> params) {
        super(params);
    }

    public double runCommand() {
        return Math.atan(Math.toRadians(myExpression.execute()));
    }
}
