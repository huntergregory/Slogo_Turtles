package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class CosineCommand extends SingleParamMathCommand {

    public CosineCommand(List<CommandInter> params) {
        super(params);
    }

    public double runCommand() {
        return Math.cos(Math.toRadians(myExpression.execute()));
    }
}
