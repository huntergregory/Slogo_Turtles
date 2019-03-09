package parser_private.commands.turtle_commands.move_distance_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class ForwardCommand extends MoveDistanceCommand {

    public ForwardCommand(List<ICommand> params) {
        super(params, true);
    }
}