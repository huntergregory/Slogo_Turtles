package parser_private;

import state.ICommand;
import state.StateManager;

import java.util.List;

public abstract class Command implements ICommand {

    protected List<ICommand> mySubCommands;
    protected StateManager myStateManager;

    public Command() {}

    public Command(List<ICommand> params) {
        this.mySubCommands = params;
    }

    @Override
    public void injectStateManager(StateManager stateManager) {
        this.myStateManager = stateManager;
        if (mySubCommands != null) {
            for (ICommand command : mySubCommands) {
                command.injectStateManager(myStateManager);
            }
        }
    }

    @Override
    public abstract double execute();

    @Override
    public int size() {
        if (mySubCommands != null) {
            return mySubCommands.size();
        }
        return 0;
    }

    @Override
    public ICommand getParam(int index) {
        return this;
    }

    @Override
    public List<ICommand> getParams() {
        return mySubCommands;
    }
}
