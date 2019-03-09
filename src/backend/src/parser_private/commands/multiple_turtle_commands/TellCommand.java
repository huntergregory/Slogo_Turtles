package parser_private.commands.multiple_turtle_commands;

import parser_private.commands.control_commands.ListCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class TellCommand extends MultipleTurtlesCommand {

    private ListCommand turtlesToTell;

    public TellCommand(List<ICommand> params) {
        super(params);
        turtlesToTell = (ListCommand)params.get(0);
    }

    @Override
    public double execute() {
        return tell(turtlesToTell);
    }


}
