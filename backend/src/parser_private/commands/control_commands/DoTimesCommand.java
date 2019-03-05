package parser_private.commands.control_commands;

import state_public.CommandInter;

import java.util.List;

public class DoTimesCommand extends IterativeCommand {

    private ListCommand myParams;

    public DoTimesCommand(List<CommandInter> params) {
        super(params);
        myParams = (ListCommand) params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double execute() {
        int limit = (int) myParams.getParam(1).execute();
        String countVarName = ((VariableCommand) myParams.getParam(0)).getVariableName();
        return iterate(countVarName, 1, limit, 1);
    }
}
