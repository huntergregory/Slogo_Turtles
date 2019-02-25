package parser.commands.control_commands;

import parser.Command;

import java.util.List;

public class ForCommand extends Command {

    ListCommand forParams;
    Command body;

    public ForCommand(List<Command> params) {
        forParams = (ListCommand)params.get(0);
        body = params.get(1);
    }

    public double runCommand() {
        int start = (int) forParams.getParam(1).execute();
        int stop = (int) forParams.getParam(2).execute();
        int increment = (int) forParams.getParam(3).execute();

        String countVarName = ((VariableCommand) forParams.getParam(0)).getVariableName();

        double retval = 0;
        for (int i = start; i < stop; i += increment) { //TODO remove duplication across dotimes, for, repeat
            myVariables.setVariable(countVarName, i);
            body.addVariables(myVariables); //propagates var changes through body commands
            retval = body.execute();
        }
        return retval;
    }

}
