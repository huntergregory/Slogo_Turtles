package parser_private;

import state_public.CommandInter;
import state_public.StateManager;

import java.util.List;

public abstract class Command implements CommandInter {

    protected List<CommandInter> mySubCommands;
    protected StateManager myStateManager;

    public Command() {}

    public Command(List<CommandInter> params) {
        this.mySubCommands = params;
    }

    @Override
    public void injectStateManager(StateManager stateManager) {
        this.myStateManager = stateManager;
        if (mySubCommands != null) {
            for (CommandInter command : mySubCommands) {
                command.injectStateManager(myStateManager);
            }
        }
    }

    @Override
    public abstract double execute();

    @Override
    public int size() {
        return 1;
    }

    @Override
    public CommandInter getParam(int index) {
        return this;
    }
}
