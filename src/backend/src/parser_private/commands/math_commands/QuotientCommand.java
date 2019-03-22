package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class QuotientCommand extends MultiParamMathCommand {

    public QuotientCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the quotient of two commands
     * @return The quotient
     */
    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double rtn = myExpressions.get(0).execute();
        for (int i = 1; i < myExpressions.size(); i++) {
            rtn /= myExpressions.get(i).execute();
        }
        return rtn;
    }
}
