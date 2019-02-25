package parser;

import java.util.List;

public abstract class Command {

    protected VariablesGroup myVariables;
    protected List<Command> mySubCommands;

    public Command() {}

    public Command(List<Command> params) {
        this.mySubCommands = params;
    }

    // Execute constructed command
    public final double execute() {
        if (mySubCommands != null)
            for (Command command : mySubCommands)
                command.setVariables(myVariables);
        return runCommand();
    }

    public abstract double runCommand();

    public void setVariables(VariablesGroup variables) {
        this.myVariables = variables;
    }

    protected double getVariable(String variable) {
        if (myVariables != null && myVariables.hasVariable(variable))
            return myVariables.getVariable(variable);
        return GlobalVariables.getInstance().getVariable(variable);
    }
}
