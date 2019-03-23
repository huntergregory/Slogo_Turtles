package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.UserCommand;

import java.util.List;

/**
 * Command that defines a new user defined command
 * @author Harry Ross
 */
public class ToCommand extends Command {

    private VariableCommand myName;
    private ListCommand myArguments;
    private ListCommand myBody;

    /**
     * Creates new To command, assigns parameters
     * @param params name, argument names, and body of new command
     */
    public ToCommand(List<Command> params) {
        myName = (VariableCommand) params.get(0);
        myArguments = (ListCommand) params.get(1);
        myBody = (ListCommand) params.get(2);
    }

    /**
     * Executes To command, saves new command to StateManager if it is not a normal SLogo command already
     * @return 1.0 if successfully created, 0.0 otherwise
     */
    @Override
    public double execute() {
        if (!myStateManager.getInputTranslator().isNormalCommand(myName.getVariableName()) && !myBody.isEmpty()) {
            myStateManager.getCommands().addCommand(myName.getVariableName(), myArguments, myBody, new UserCommand());
            return 1.0; // Successful creation
        }
        return 0.0;
    }
}
