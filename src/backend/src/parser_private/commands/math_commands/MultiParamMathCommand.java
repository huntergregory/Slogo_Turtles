package parser_private.commands.math_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
abstract class MultiParamMathCommand extends Command {

    List<ICommand> myExpressions;

    MultiParamMathCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    abstract public double execute();
}
