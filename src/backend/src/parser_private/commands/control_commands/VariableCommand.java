package parser_private.commands.control_commands;

import parser_private.Command;

/**
 * @author Harry Ross
 */
public class VariableCommand extends Command {

    private String myName;

    public VariableCommand(String name) {
        this.myName = name;
    }

    public String getVariableName() {
        return myName;
    }

    @Override
    public double execute() {
        return myStateManager.getVariables().getVariable(myName);
    }
}
