package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

public class QuotientCommand extends MultiParamMathCommand {

    public QuotientCommand(List<ICommand> params) {
        super(params);
    }

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
