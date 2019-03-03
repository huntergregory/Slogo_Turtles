package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

abstract class TwoParamMathCommand extends Command {

    CommandInter myExpression1;
    CommandInter myExpression2;

    TwoParamMathCommand(List<CommandInter> params) {
        super(params);
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }
}
