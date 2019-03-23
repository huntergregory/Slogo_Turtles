package parser_private.commands.control_commands;

import parser_private.Command;

/**
 * Command that references a user defined variable
 * @author Harry Ross
 * @author David Miron
 */
public class VariableCommand extends Command {

    private String myName;

    /**
     * Creates a new Variable command, assigns name to instance variable
     * @param name String name of variable being referenced
     */
    public VariableCommand(String name) {
        this.myName = name;
    }

    /**
     * Get the name of the specified variable
     * @return String name of the specified variable
     */
    public String getVariableName() {
        return myName;
    }

    /**
     * Return the value of the specified variable
     * @return Double value of the specified variable
     */
    @Override
    public double execute() {
        return myStateManager.getVariables().getVariable(myName);
    }
}
