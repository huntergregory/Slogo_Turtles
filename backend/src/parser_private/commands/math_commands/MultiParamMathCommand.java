package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

abstract class MultiParamMathCommand extends Command {

    List<CommandInter> myExpressions;

    MultiParamMathCommand(List<CommandInter> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    abstract public double execute();
}
