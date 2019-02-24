package parser.commands.control_commands;

import parser.Command;
import parser.VariablesGroup;
import parser.commands.VariableCommand;

import java.util.List;

public class DoTimesCommand extends Command {

    private ListCommand varParams;
    private Command body;

    public DoTimesCommand(List<Command> params) {
        super(params);
        varParams = (ListCommand)params.get(0);
        body = params.get(1);
    }

    @Override
    public double runCommand() {

        int limit = (int)varParams.getParam(1).execute();
        String countVarName = ((VariableCommand)varParams.getParam(0)).getVariableName();

        for (int i = 1; i < limit; i++) {

            VariablesGroup vars = new VariablesGroup();
            vars.setVariable(countVarName, i);
            body.setVariables(vars);
            body.execute();
        }

        VariablesGroup vars = new VariablesGroup();
        vars.setVariable(countVarName, limit);
        body.setVariables(vars);
        return body.execute();
    }
}
