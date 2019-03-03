package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class RepeatCommand extends Command {

    private CommandInter myTotalIter;
    private CommandInter myBody;

    public RepeatCommand(List<CommandInter> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";
        double retval = 0;

        for (int i = 1; i <= limit; i++) { //TODO remove duplication across dotimes, for, repeat
            // --- UNCOMMENT TO ENABLE LOCAL VARIABLE SCOPE ---
            /*myVariables.setVariable(countVarName, i);
              myBody.addVariables(myVariables);*/
            myStateManager.getVariables().setVariable(countVarName, i); // --- COMMENT THIS TO ENABLE LOCAL ---
            retval = myBody.execute();
        }
        return retval; // Final iteration
    }
}
