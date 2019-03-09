package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class SumCommand extends MultiParamMathCommand {

    public SumCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        double rtn = 0;
        for (ICommand exp : myExpressions) {
            rtn += exp.execute();
        }
        return rtn;
    }
}
