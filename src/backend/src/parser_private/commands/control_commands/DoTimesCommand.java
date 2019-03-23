package parser_private.commands.control_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that executes its body the number of times specified as a parameter and updates a specified variable with each iteration
 * @author Harry Ross
 */
public class DoTimesCommand extends IterativeCommand {

    private ListCommand myParams;

    /**
     * Creates DoTimes command, assigns parameters
     * @param params parameters to control loop
     */
    public DoTimesCommand(List<ICommand> params) {
        super(params);
        myParams = (ListCommand) params.get(0);
        myBody = params.get(1);
    }

    /**
     * Executes body of command the number of times specified in parameters
     * @return output of final command in body
     */
    @Override
    public double execute() {
        int limit = (int) myParams.getParam(1).execute();
        String countVarName = ((VariableCommand) myParams.getParam(0)).getVariableName();
        return iterate(countVarName, 1, limit, 1);
    }
}
