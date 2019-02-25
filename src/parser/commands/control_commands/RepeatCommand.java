package parser.commands.control_commands;

import parser.Command;
import java.util.List;

public class RepeatCommand extends Command {

    private Command myTotalIter;
    private Command myBody;
    private Command myCurrentIter;

    public RepeatCommand(List<Command> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";

        for (int i = 1; i < limit; i++) { //TODO remove duplication
            myVariables.setVariable(countVarName, i);
            myBody.addVariables(myVariables);
            myBody.execute();
        }
        myVariables.setVariable(countVarName, limit);
        myBody.addVariables(myVariables);
        return myBody.execute(); // Final iteration
    }
}
