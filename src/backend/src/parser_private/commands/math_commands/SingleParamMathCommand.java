package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

abstract class SingleParamMathCommand extends Command {

    ICommand myExpression;

    SingleParamMathCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
    }
}
