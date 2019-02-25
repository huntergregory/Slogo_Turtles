package parser.commands.control_commands;

import parser.Command;
import java.util.List;

public class DoTimesCommand extends Command {

    private ListCommand myParams;
    private Command myBody;

    public DoTimesCommand(List<Command> params) {
        super(params);
        myParams = (ListCommand) params.get(0); // dotimes >>>[]<<< []
        myBody = params.get(1); // dotimes [] >>>[]<<<
    }

    @Override
    public double runCommand() {
        int limit = (int) myParams.getParam(1).execute();
        String countVarName = ((VariableCommand) myParams.getParam(0)).getVariableName();

        double retval = 0;
        for (int i = 1; i <= limit; i++) { //TODO remove duplication across dotimes, for, repeat
            myVariables.setVariable(countVarName, i);
            myBody.addVariables(myVariables); //propagates var changes through body commands
            retval = myBody.execute();
        }
        return retval;
    }
}
