package parser_private.commands.control_commands;

import parser_private.Command;
import parser_public.GlobalCommands;

import java.util.List;

public class ToCommand extends Command {

    private ListCommand myBody;
    private ListCommand myVariables;
    private VariableCommand myName;

    public ToCommand(List<Command> params) {
        myName = (VariableCommand)params.get(0);
        myVariables = (ListCommand)params.get(1);
        myBody = (ListCommand)params.get(2);

    }

    @Override
    public double runCommand() {
        GlobalCommands.getInstance().addCommand(myName.getVariableName(), new UserDefinedCommand(myVariables, myBody));
        return 1;
    }
}
