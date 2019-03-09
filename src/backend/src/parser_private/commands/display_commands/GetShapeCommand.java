package parser_private.commands.display_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 * @author Harry Ross
 */
public class GetShapeCommand extends Command {

    public GetShapeCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return 0.0;
    }
}
