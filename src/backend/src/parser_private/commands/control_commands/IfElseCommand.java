package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class IfElseCommand extends Command {

    private ICommand myExpression;
    private ICommand myTrue;
    private ICommand myFalse;

    public IfElseCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
        myTrue = params.get(1);
        myFalse = params.get(2);
    }

    @Override
    public double execute() {
        if (myExpression.execute() != 0) {
            return myTrue.execute();
        }
        return myFalse.execute();
    }
}
