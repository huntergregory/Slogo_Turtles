package parser_private.commands.math_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Class to represent math commands with two params
 * @author David Miron
 */
abstract class TwoParamMathCommand extends Command {

    ICommand myExpression1;
    ICommand myExpression2;

    TwoParamMathCommand(List<ICommand> params) {
        super(params);
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }
}
