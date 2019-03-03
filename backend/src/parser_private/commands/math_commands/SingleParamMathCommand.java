package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

abstract class SingleParamMathCommand extends Command {

    CommandInter myExpression;

    SingleParamMathCommand(List<CommandInter> params) {
        super(params);
        myExpression = params.get(0);
    }
}
