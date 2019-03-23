package parser_private.commands.boolean_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * General command for a boolean command that takes exactly two parameters
 * @author Harry Ross
 */
abstract class TwoParamBoolCommand extends Command {

    ICommand myExpression1;
    ICommand myExpression2;

    TwoParamBoolCommand(List<ICommand> params) {
        super(params);
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }

    /**
     * Executes two parameter boolean command
     * @return depends on implementation in children classes
     */
    abstract public double execute();
}
