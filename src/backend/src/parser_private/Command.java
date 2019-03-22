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
<<<<<<< HEAD
     * Pass the state manager to this command when it is created in the factory
     * @param stateManager The state manager
=======
     * Injects StateManager object from parent command into all nested commands to share variables, command space.
     * @param stateManager StateManager object to be injected
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
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
<<<<<<< HEAD
     * Execute the command, calling subcommands' execute methods if necessary
     * @return The return code of the current command
=======
     * Executes given Command
     * @return Output of Command execution as a double, depends on Command type
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
     */
    @Override
    public abstract double execute();

    /**
<<<<<<< HEAD
     * Get the number of subcommands
     * @return The number of subcommands
=======
     * Returns size of a Command (number of subcommands) or 0 if no subcommands exist
     * @return Number of subcommands
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
     */
    @Override
    public int size() {
        if (mySubCommands != null) {
            return mySubCommands.size();
        }
        return 0;
    }

    /**
<<<<<<< HEAD
     * Get a param
     * @param index The index
     * @return The param
=======
     * Returns self unless overridden in child command
     * @param index Index of requested parameter (meaningless in this context)
     * @return ICommand of parameter at requested index
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
     */
    @Override
    public ICommand getParam(int index) {
        return this;
    }

    /**
<<<<<<< HEAD
     * Get a list of params
     * @return All subcommand params
=======
     * Returns list of all parameters and nested commands of a Command object
     * @return List of subcommands
>>>>>>> adf14969360f39950cfce8a86eda3576c1036e41
     */
    @Override
    public List<ICommand> getParams() {
        return mySubCommands;
    }
}
