package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

abstract class IterativeCommand extends Command {

    ICommand myBody;

    IterativeCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    abstract public double execute();

    double iterate(String countVarName, int start, int stop, int increment) {
        double rtn = 0;
        for (int i = start; i <= stop; i += increment) {
            myStateManager.getVariables().setVariable(countVarName, i);
            rtn = myBody.execute();
        }
        return rtn;
    }
}
