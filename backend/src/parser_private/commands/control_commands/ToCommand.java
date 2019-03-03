package parser_private.commands.control_commands;

import parser_private.Command;
<<<<<<< HEAD
import parser_private.StoredUserDefinedCommand;
=======
>>>>>>> cb188fb5b4f67c85c58639f7cffa30cbc8b0122e

import java.util.List;

public class ToCommand extends Command {

    private VariableCommand myName;
    private ListCommand myArguments;
    private ListCommand myBody;

    public ToCommand(List<Command> params) {
        myName = (VariableCommand) params.get(0);
        myArguments = (ListCommand) params.get(1);
        myBody = (ListCommand) params.get(2);
    }

    @Override
    public double runCommand() {
        if (myBody.isEmpty()) {
            return 0; // Failed to create new user command because empty body
        }
<<<<<<< HEAD
        myStateManager.getCommands().addCommand(myName.getVariableName(), new StoredUserDefinedCommand(myArguments, myBody));
=======
        GlobalCommands.getInstance().addCommand(myName.getVariableName(), myArguments, myBody);
>>>>>>> cb188fb5b4f67c85c58639f7cffa30cbc8b0122e
        return 1; // Successful creation
    }
}
