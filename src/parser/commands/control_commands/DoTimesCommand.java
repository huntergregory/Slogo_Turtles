package parser.commands.control_commands;

import parser.Command;
import parser.VariablesGroup;
import java.util.List;

public class DoTimesCommand extends Command {

    private ListCommand myParams;
    private Command myBody;

    public DoTimesCommand(List<Command> params) {
        super(params);
        myParams = (ListCommand)params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        int limit = (int) myParams.getParam(1).execute();
        String countVarName = ((VariableCommand) myParams.getParam(0)).getVariableName();

        for (int i = 1; i < limit; i++) { //TODO remove duplication
            VariablesGroup vars = new VariablesGroup();
            vars.setVariable(countVarName, i);
            myBody.setVariables(vars); //propagates var changes through myBody mySubCommands
            myBody.execute();
        }
        VariablesGroup vars = new VariablesGroup();
        vars.setVariable(countVarName, limit);
        myBody.setVariables(vars);
        return myBody.execute();
    }
}
