package parser_private;

import state.ICommand;
import state.StateManager;

import java.util.List;

/**
<<<<<<< HEAD
 * Class to represent an abstract command
=======
 * Class for Command object supertype in SLogo environment, implements parameters and is able to execute itself when asked.
 * For the sake of this class and its children, a "subcommand" is a Command that is either a parameter
 * or a nested Command of a parent Command.
 * Dependencies: StateManager
 * Ex. Command command = new Command(new List<ICommand>());
 *     command.execute();
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
 * @author David Miron
 * @author Harry Ross
 */
public abstract class Command implements ICommand {

    protected List<ICommand> mySubCommands;
    protected StateManager myStateManager;

    /**
     * Empty default constructor for Command object
     */
    public Command() {}

    /**
     * Creates new Command with specified parameters, given in order as a list of ICommand.
     * @param params list of parameters
     */
    public Command(List<ICommand> params) {
        this.mySubCommands = params;
    }

    /**
     * Injects StateManager object from parent command into all nested commands to share variables, command space.
     * @param stateManager StateManager object to be injected
     */
    @Override
    public void injectStateManager(StateManager stateManager) {
        this.myStateManager = stateManager;
        if (mySubCommands != null) {
            for (ICommand command : mySubCommands) {
                command.injectStateManager(myStateManager);
            }
        }
    }

    /**
     * Executes given Command
     * @return Output of Command execution as a double, depends on Command type
     */
    @Override
    public abstract double execute();

    /**
     * Returns size of a Command (number of subcommands) or 0 if no subcommands exist
     * @return Number of subcommands
     */
    @Override
    public int size() {
        if (mySubCommands != null) {
            return mySubCommands.size();
        }
        return 0;
    }

    /**
     * Returns self unless overridden in child command
     * @param index Index of requested parameter (meaningless in this context)
     * @return ICommand of parameter at requested index
     */
    @Override
    public ICommand getParam(int index) {
        return this;
    }

    /**
     * Returns list of all parameters and nested commands of a Command object
     * @return List of subcommands
     */
    @Override
    public List<ICommand> getParams() {
        return mySubCommands;
    }
}
