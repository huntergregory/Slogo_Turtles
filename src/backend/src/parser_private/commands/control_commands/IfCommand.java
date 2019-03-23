package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that executes body if the expression defined in the parameters evaluates to nonzero
 * @author Harry Ross
 */
public class IfCommand extends Command {

    private ICommand myExpression;
    private ICommand myBody;

    /**
     * Creates If command, assigns parameters
     * @param params parameters to use in comparison and execution
     */
    public IfCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
        myBody = params.get(1);
    }

    /**
     * Executes If command
     * @return output of final command in body of if statement if expression is nonzero, 0.0 otherwise
     */
    @Override
    public double execute() {
        if (myExpression.execute() != 0) {
            return myBody.execute();
        }
        return 0;
    }
}
