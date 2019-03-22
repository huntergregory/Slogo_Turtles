package parser_private.commands.multiple_turtle_commands;

import parser_private.commands.control_commands.ListCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class AskCommand extends MultipleTurtlesCommand {

    private ListCommand myTurtles;
    private ListCommand myCommands;

    public AskCommand(List<ICommand> params) {
        super(params);
        myTurtles = (ListCommand)params.get(0);
        myCommands = (ListCommand)params.get(1);
    }

    /**
     * Run a set of commands on turtles with certain IDs
     * @return The result of the last executed command
     */
    @Override
    public double execute() {
        return ask(myTurtles, myCommands);
    }


}
