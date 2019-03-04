package parser_private;

import state_public.CommandInter;
import state_public.StateManager;
import state_public.VariablesGroup;

import java.util.List;

public abstract class Command implements CommandInter {

    protected VariablesGroup myVariables = new VariablesGroup();
    protected List<CommandInter> mySubCommands;
    protected StateManager myStateManager;

    public Command() {}

    public Command(List<CommandInter> params) {
        this.mySubCommands = params;
    }

    @Override
    public void injectStateManager(StateManager stateManager) { //TODO
        this.myStateManager = stateManager;
        if (mySubCommands != null) {
            for (CommandInter command : mySubCommands) {
                command.injectStateManager(myStateManager);
            }
        }
    }

    // Execute constructed command
    @Override
    public final double execute() {
        if (mySubCommands != null) {
            for (CommandInter command : mySubCommands) {
                command.addVariables(myVariables);
            }
        }
        return runCommand();
    }

    @Override
    public abstract double runCommand();

    @Override
    public void addVariables(VariablesGroup variables) {
        this.myVariables = variables; // Share same VariablesGroup between subcommands (for local scope)
    }

    @Override
    public double getVariable(String variable) {
        if (myVariables.hasVariable(variable)) {
            return myVariables.getVariable(variable);
        }
        return myStateManager.getVariables().getVariable(variable);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public CommandInter getParam(int index) {
        return this;
    }
}
