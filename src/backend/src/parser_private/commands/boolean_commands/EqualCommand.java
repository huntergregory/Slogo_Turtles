package parser_private.commands.boolean_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that checks if all parameters are equal to one another
 * @author Harry Ross
 */
public class EqualCommand extends Command {

    private List<ICommand> myExpressions;

    /**
     * Creates an Equal command, assigns parameters
     * @param params parameters to be compared
     */
    public EqualCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    /**
     * Executes Equal command, compares parameters
     * @return 1.0 if all parameters are equal, 0.0 otherwise
     */
    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double eq = myExpressions.get(0).execute();
        for (ICommand exp : myExpressions) {
            if (exp.execute() != eq) {
                return 0;
            }
        }
        return 1;
    }
}
