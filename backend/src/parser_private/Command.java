package parser_private;

import state_public.CommandInter;
import state_public.VariablesGroup;

import java.util.List;

public abstract class Command implements CommandInter {

    protected VariablesGroup myVariables = new VariablesGroup();
    protected List<CommandInter> mySubCommands;

    public Command() {}

    public Command(List<CommandInter> params) {
        this.mySubCommands = params;
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

    protected double getVariable(String variable) {
        if (myVariables.hasVariable(variable)) {
            return myVariables.getVariable(variable);
        }
        return GlobalVariables.getInstance().getVariable(variable);
    }
}
