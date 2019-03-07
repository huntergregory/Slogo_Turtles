package parser_private.commands.multiple_turtle_commands;

import parser_private.commands.control_commands.ListCommand;
import state_public.ICommand;

import java.util.List;

public class AskCommand extends MultipleTurtlesCommand {

    private ListCommand myTurtles;
    private ListCommand myCommands;

    public AskCommand(List<ICommand> params) {
        super(params);
        myTurtles = (ListCommand)params.get(0);
        myCommands = (ListCommand)params.get(1);
    }

    @Override
    public double execute() {
        return ask(myTurtles, myCommands);
    }


}
