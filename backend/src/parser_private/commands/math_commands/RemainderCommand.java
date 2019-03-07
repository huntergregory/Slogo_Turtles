package parser_private.commands.math_commands;

import state_public.ICommand;

import java.util.List;

public class RemainderCommand extends MultiParamMathCommand {

    public RemainderCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double rtn = myExpressions.get(0).execute();
        for (int i = 1; i < myExpressions.size(); i++) {
            rtn %= myExpressions.get(i).execute();
        }
        return rtn;
    }
}
