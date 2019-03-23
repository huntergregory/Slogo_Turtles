package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that returns 1.0 if any single parameter is nonzero
 * @author Harry Ross
 */
public class OrCommand extends AndCommand {

    private List<ICommand> myExpressions;

    /**
     * Creates Or command, assigns parameters
     * @param params parameters to be compared
     */
    public OrCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Executes Or command
     * @return 1.0 is any parameter is nonzero, 0.0 otherwise
     */
    @Override
    public double execute() {
        super.makeOr();
        return super.execute();
    }
}
