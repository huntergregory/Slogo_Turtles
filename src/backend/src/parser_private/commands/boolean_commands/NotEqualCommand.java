package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that checks if any parameters are not equal to one another
 * @author Harry Ross
 */
public class NotEqualCommand extends EqualCommand {

    /**
     * Creates NotEqual command, assigns parameters
     * @param params parameters to be compared
     */
    public NotEqualCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Executes NotEqual command
     * @return 1.0 if any single parameter is not equal to another
     */
    @Override
    public double execute() {
        return (super.execute() == 0) ? 1 : 0;
    }
}
