package parser;

import java.util.List;

public abstract class Command {

    protected VariablesGroup myVariables = new VariablesGroup();
    protected List<Command> mySubCommands;

    public Command() {}

    public Command(List<Command> params) {
        this.mySubCommands = params;
    }

    // Execute constructed command
    public final double execute() {
        if (mySubCommands != null) { //TODO can this be empty instead of null?
            for (Command command : mySubCommands) {
                command.addVariables(myVariables);
            }
        }
        return runCommand();
    }

    public abstract double runCommand();

    public void addVariables(VariablesGroup variables) {
        this.myVariables.add(variables);
    }

    protected double getVariable(String variable) {
        if (myVariables.hasVariable(variable)) {
            return myVariables.getVariable(variable);
        }
        return GlobalVariables.getInstance().getVariable(variable);
    }
}
