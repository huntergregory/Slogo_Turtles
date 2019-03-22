package parser_private;

import state.ICommand;
import state.StateManager;

import java.util.List;

/**
 * Class to represent an abstract command
 * @author David Miron
 * @author Harry Ross
 */
public abstract class Command implements ICommand {

    protected List<ICommand> mySubCommands;
    protected StateManager myStateManager;

    public Command() {}

    public Command(List<ICommand> params) {
        this.mySubCommands = params;
    }

    /**
     * Pass the state manager to this command when it is created in the factory
     * @param stateManager The state manager
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
     * Execute the command, calling subcommands' execute methods if necessary
     * @return The return code of the current command
     */
    @Override
    public abstract double execute();

    /**
     * Get the number of subcommands
     * @return The number of subcommands
     */
    @Override
    public int size() {
        if (mySubCommands != null) {
            return mySubCommands.size();
        }
        return 0;
    }

    /**
     * Get a param
     * @param index The index
     * @return The param
     */
    @Override
    public ICommand getParam(int index) {
        return this;
    }

    /**
     * Get a list of params
     * @return All subcommand params
     */
    @Override
    public List<ICommand> getParams() {
        return mySubCommands;
    }
}
