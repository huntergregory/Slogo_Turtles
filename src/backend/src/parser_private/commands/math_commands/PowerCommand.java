package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class PowerCommand extends MultiParamMathCommand {

    public PowerCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double rtn = myExpressions.get(0).execute();
        for (int i = 1; i < myExpressions.size(); i++) {
            rtn = Math.pow(rtn, myExpressions.get(i).execute());
        }
        return rtn;
    }
}
