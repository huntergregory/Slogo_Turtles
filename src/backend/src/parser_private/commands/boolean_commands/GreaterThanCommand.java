package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that checks if one input is greater than another
 * @author Harry Ross
 */
public class GreaterThanCommand extends TwoParamBoolCommand {

    /**
     * Creates GreaterThan command, assigns parameters
     * @param params parameters to be compared
     */
    public GreaterThanCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Executes GreaterThan command
     * @return 1.0 if first parameter is greater than second, 0.0 otherwise
     */
    @Override
    public double execute() {
        return (myExpression1.execute() > myExpression2.execute()) ? 1 : 0;
    }
}
