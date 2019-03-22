package parser_private;

import parser_private.commands.control_commands.VariableCommand;
import state.ICommand;
import state.IUserCommand;

import java.util.List;

/**
 * Class for a user defined command in SLogo environment.
 * As this object type is used for all user defined commands, it is important that it is general enough to be used
 * for any number of parameters and without a predefined set of operations.
 * For the purposes of this class, "arguments" are the labels for each parameter that can be referenced elsewhere
 * in the code, and "parameters" are the specific values assigned to these arguments when a user defined command
 * is instantiated.
 * Dependencies: Command interfaces and object types.
 * Ex. IUserCommand command = new UserCommand();
 *     command.applyArgsAndBody(args, body); // Assume args, body, and params are initialized
 *     command.assignParams(params);
 *     command.execute();
 * @author Harry Ross
 */
public class UserCommand extends Command implements IUserCommand {

    private ICommand myArguments;
    private ICommand myBody;

    /**
     * Empty default constructor for user defined command
     */
    public UserCommand() {}

    private UserCommand(UserCommand copy) {
        applyArgsAndBody(copy.myArguments, copy.myBody);
    }

    /**
     * Assigns parameters to a user defined command
     * @param params List of parameters
     */
    @Override
    public void assignParams(List<ICommand> params) {
        this.mySubCommands = params;
        this.mySubCommands.add(myBody); // Since this can't happen in the constructor in this case
    }

    /**
     * Sets arguments and body for a user defined command
     * @param args Arguments to be applied as ICommand
     * @param body Body of user defined command, which is stored elsewhere and applied when command created
     */
    @Override
    public void applyArgsAndBody(ICommand args, ICommand body) {
        myArguments = args;
        myBody = body;
    }

    /**
     * Returns a new instance of a given user defined command from current instance
     * @return New instance of self with independent parameters
     */
    @Override
    public IUserCommand getNewInstance() {
        return new UserCommand(this);
    }

    /**
     * Returns size of arguments list as integer
     * @return Size of arguments list for this user defined command
     */
    @Override
    public int size() {
        return myArguments.size();
    }

    /**
     * Sets variables in StateManager to values passed in as parameters, executes user defined command
     * @return Return value of user defined command, dependant on command body
     */
    @Override
    public double execute() {
        int numRealParams = mySubCommands.size() - 1;
        for (int i = 0; i < Math.min(numRealParams, myArguments.size()); i++) {
            String varName = ((VariableCommand) myArguments.getParam(i)).getVariableName();
            myStateManager.getVariables().setVariable(varName, mySubCommands.get(i).execute());
        }
        return myBody.execute();
    }
}
