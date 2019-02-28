package parser_private.commands.control_commands;

import parser_private.Command;
import parser_public.GlobalVariables;
import java.util.List;

public class ForCommand extends Command {

    ListCommand forParams;
    Command myBody;

    public ForCommand(List<Command> params) {
        super(params);
        forParams = (ListCommand)params.get(0);
        myBody = params.get(1);
    }

    public double runCommand() {
        int start = (int) forParams.getParam(1).execute();
        int stop = (int) forParams.getParam(2).execute();
        int increment = (int) forParams.getParam(3).execute();
        String countVarName = ((VariableCommand) forParams.getParam(0)).getVariableName();
        double retval = 0;

        for (int i = start; i <= stop; i += increment) { //TODO remove duplication across dotimes, for, repeat
            // --- UNCOMMENT TO ENABLE LOCAL VARIABLE SCOPE ---
            /*myVariables.setVariable(countVarName, i);
              myBody.addVariables(myVariables);*/ //propagates var changes through body commands
            GlobalVariables.getInstance().setVariable(countVarName, i); // --- COMMENT THIS TO ENABLE LOCAL ---
            retval = myBody.execute();
        }
        return retval;
    }
}
