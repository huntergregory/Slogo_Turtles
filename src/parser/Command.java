package parser;

import java.util.List;

public abstract class Command {

    protected VariablesGroup variables;
    protected List<Command> commands;

    public Command() {}

    public Command(List<Command> params) {
        this.commands = params;
    }

    // Execute constructed command
    public final double execute() {
        if (commands != null)
            for (Command command : commands)
                command.setVariables(variables);
        return runCommand();
    }

    public abstract double runCommand();

    public void setVariables(VariablesGroup variables) {
        this.variables = variables;
    }

    protected double getVariable(String variable) {
        if (variables != null && variables.hasVariable(variable))
            return variables.getVariable(variable);
        return GlobalVariables.getInstance().getVariable(variable);
    }
}
