package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that checks if one input is less than another
 * @author Harry Ross
 */
public class LessThanCommand extends TwoParamBoolCommand {

    /**
     * Creates LessThan command, assigns parameters
     * @param params parameters to be compared
     */
    public LessThanCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Executes LessThan command
     * @return 1.0 if first parameter is less than second, 0.0 otherwise
     */
    @Override
    public double execute() {
        return (myExpression1.execute() < myExpression2.execute()) ? 1 : 0;
    }
}
