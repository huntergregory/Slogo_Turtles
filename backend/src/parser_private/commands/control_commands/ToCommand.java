package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.StoredUserDefinedCommand;
import parser_public.GlobalCommands;

import java.util.List;

public class ToCommand extends Command {

    private VariableCommand myName;
    private ListCommand myArguments;
    private ListCommand myBody;

    public ToCommand(List<Command> params) {
        myName = (VariableCommand)params.get(0);
        myArguments = (ListCommand)params.get(1);
        myBody = (ListCommand)params.get(2);

    }

    @Override
    public double runCommand() {
        GlobalCommands.getInstance().addCommand(myName.getVariableName(), new StoredUserDefinedCommand(myArguments, myBody));
        return 1;
    }
}
