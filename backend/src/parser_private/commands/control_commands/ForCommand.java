package parser_private.commands.control_commands;

import state_public.CommandInter;

import java.util.List;

public class ForCommand extends IterativeCommand {

    private ListCommand forParams;

    public ForCommand(List<CommandInter> params) {
        super(params);
        forParams = (ListCommand)params.get(0);
        myBody = params.get(1);
    }

    public double runCommand() {
        int start = (int) forParams.getParam(1).execute();
        int stop = (int) forParams.getParam(2).execute();
        int increment = (int) forParams.getParam(3).execute();
        String countVarName = ((VariableCommand) forParams.getParam(0)).getVariableName();
        return iterate(countVarName, start, stop, increment);
    }
}
