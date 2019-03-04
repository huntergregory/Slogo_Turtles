package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

abstract class IterativeCommand extends Command {

    CommandInter myBody;

    IterativeCommand(List<CommandInter> params) {
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
