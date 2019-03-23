package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that executes one body if expression is nonzero, second body if zero
 * @author Harry Ross
 */
public class IfElseCommand extends Command {

    private ICommand myExpression;
    private ICommand myTrue;
    private ICommand myFalse;

    /**
     * Creates IfElse command, assigns parameters
     * @param params parameters to use in comparison and execution
     */
    public IfElseCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
        myTrue = params.get(1);
        myFalse = params.get(2);
    }

    /**
     * Executes IfElse command
     * @return output of final command of first body parameter if expression is nonzero, otherwise output
     * of final command of second body parameter
     */
    @Override
    public double execute() {
        if (myExpression.execute() != 0) {
            return myTrue.execute();
        }
        return myFalse.execute();
    }
}
