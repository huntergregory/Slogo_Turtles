package parser_private.commands.control_commands;

import state.ICommand;

import java.util.List;

/**
 * Command that repeats the number of times specified as a parameter, updates :repcount variable with each iteration
 * @author Harry Ross
 */
public class RepeatCommand extends IterativeCommand {

    private ICommand myTotalIter;

    /**
     * Creates new Repeat command, assigns parameters
     * @param params parameters to control loop
     */
    public RepeatCommand(List<ICommand> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    /**
     * Executes Repeat command
     * @return output of final command in body
     */
    @Override
    public double execute() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";
        return iterate(countVarName, 1, limit, 1);
    }
}
