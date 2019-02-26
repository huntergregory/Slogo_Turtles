package parser_private;

import parser_public.GlobalVariables;
import java.util.List;

public abstract class Command {

    private VariablesGroup myVariables = new VariablesGroup();
    protected List<Command> mySubCommands;

    public Command() {}

    public Command(List<Command> params) {
        this.mySubCommands = params;
    }

    // Execute constructed command
    public final double execute() {
        if (mySubCommands != null) {
            for (Command command : mySubCommands) {
                command.addVariables(myVariables);
            }
        }
        return runCommand();
    }

    public abstract double runCommand();

    private void addVariables(VariablesGroup variables) {
        this.myVariables = variables; // Share same parser_private.VariablesGroup between subcommands (for local scope)
    }

    protected double getVariable(String variable) {
        if (myVariables.hasVariable(variable)) {
            return myVariables.getVariable(variable);
        }
        return GlobalVariables.getInstance().getVariable(variable);
    }
}
