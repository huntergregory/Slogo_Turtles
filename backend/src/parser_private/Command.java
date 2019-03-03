package parser_private;

import state_public.CommandInter;
import state_public.GlobalVariables;
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

    public void injectStateManager(StateManager stateManager) {
        this.myStateManager = stateManager;
    }

    // Execute constructed command
    public final double execute() {
        if (mySubCommands != null) {
            for (CommandInter command : mySubCommands) {
                command.addVariables(myVariables);
            }
        }
        return runCommand();
    }

    public abstract double runCommand();

    public void addVariables(VariablesGroup variables) {
        this.myVariables = variables; // Share same VariablesGroup between subcommands (for local scope)
    }

    public double getVariable(String variable) {
        if (myVariables.hasVariable(variable)) {
            return myVariables.getVariable(variable);
        }
        return myStateManager.getVariables().getVariable(variable);
    }
}
