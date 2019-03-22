package parser_private.commands.control_commands;

import parser_private.Command;

/**
 * @author Harry Ross
 * @author David Miron
 */
public class VariableCommand extends Command {

    private String myName;

    public VariableCommand(String name) {
        this.myName = name;
    }

    /**
     * Get the name of the current variable
     * @return The name of the current variable
     */
    public String getVariableName() {
        return myName;
    }

    /**
     * Return the value of the current variable
     * @return The value of the current variable
     */
    @Override
    public double execute() {
        return myStateManager.getVariables().getVariable(myName);
    }
}
