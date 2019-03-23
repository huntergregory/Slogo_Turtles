package parser_private.commands.boolean_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that outputs 1.0 if all inputs are true or 0.0 if one or more inputs is false.
 * @author Harry Ross
 */
public class AndCommand extends Command {

    private List<ICommand> myExpressions;
    private int orToggle;

    /**
     * Creates an And command, assigns parameters
     * @param params parameters to be compared
     */
    public AndCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
        orToggle = 0;
    }

    void makeOr() {
        orToggle = 1;
    }

    /**
     * Executes And command, compares parameters
     * @return 1.0 if all parameters are greater than 0, 0.0 otherwise
     */
    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        for (ICommand exp : myExpressions) {
            if (exp.execute() == orToggle) {
                return orToggle;
            }
        }
        return (orToggle == 1) ? 0 : 1;
    }
}
