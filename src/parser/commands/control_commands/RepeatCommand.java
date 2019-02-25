package parser.commands.control_commands;

import parser.Command;
import java.util.List;

public class RepeatCommand extends Command {

    private Command myTotalIter;
    private Command myBody;

    public RepeatCommand(List<Command> params) {
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
            myVariables.setVariable(countVarName, i);
            myBody.addVariables(myVariables);
            retval = myBody.execute();
        }
        return retval;
    }
}
