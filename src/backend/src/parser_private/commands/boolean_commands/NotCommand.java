package parser_private.commands.boolean_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that outputs the boolean opposite of input
 * @author Harry Ross
 */
public class NotCommand extends Command {

    private ICommand myExpression;

    /**
     * Creates Not command, assigns parameters
     * @param params parameter to be compared
     */
    public NotCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
    }

    /**
     * Executes Not command
     * @return 1.0 if parameter is 0, 0.0 otherwise
     */
    @Override
    public double execute() {
        return (myExpression.execute() == 0) ? 1 : 0;
    }
}
