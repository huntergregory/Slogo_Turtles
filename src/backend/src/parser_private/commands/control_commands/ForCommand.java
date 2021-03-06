package parser_private.commands.control_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that executes its body the number of times specified as a parameter and updates a specified variable with each iteration, the
 * spacing of which is also specified as a parameter
 * @author David Miron
 * @author Harry Ross
 */
public class ForCommand extends IterativeCommand {

    private ListCommand forParams;

    /**
     * Creates For command, assigns parameters
     * @param params parameters to control loop
     */
    public ForCommand(List<ICommand> params) {
        super(params);
        forParams = (ListCommand)params.get(0);
        myBody = params.get(1);
    }


    /**
     * Run a body command multiple commands as if in a for loop, setting a variable each time
     * @return The return value of the last command
     */
    public double execute() {
        int start = (int) forParams.getParam(1).execute();
        int stop = (int) forParams.getParam(2).execute();
        int increment = (int) forParams.getParam(3).execute();
        String countVarName = ((VariableCommand) forParams.getParam(0)).getVariableName();
        return iterate(countVarName, start, stop, increment);
    }
}
