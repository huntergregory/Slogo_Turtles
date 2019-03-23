package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that assigns a user defined variable to a given value when executed
 * @author Harry Ross
 */
public class MakeVariableCommand extends Command {

    private String myName;
    private ICommand myExpression;

    /**
     * Creates new MakeVariable command, assigns parameters
     * @param params variable name and value to be set
     */
    public MakeVariableCommand(List<ICommand> params) {
        super(params);
        myName = ((VariableCommand) params.get(0)).getVariableName();
        myExpression = params.get(1);
    }

    /**
     * Executes MakeVariable command
     * @return value that specified command has been set to
     */
    @Override
    public double execute() {
        myStateManager.getVariables().setVariable(myName, myExpression.execute());
        return myStateManager.getVariables().getVariable(myName);
    }
}
