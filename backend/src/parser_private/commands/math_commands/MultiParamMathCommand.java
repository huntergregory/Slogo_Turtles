package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

abstract class MultiParamMathCommand extends Command {

    List<ICommand> myExpressions;

    MultiParamMathCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    abstract public double execute();
}
