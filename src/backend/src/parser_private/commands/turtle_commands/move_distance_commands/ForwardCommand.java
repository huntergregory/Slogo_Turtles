package parser_private.commands.turtle_commands.move_distance_commands;

import state_public.ICommand;

import java.util.List;

public class ForwardCommand extends MoveDistanceCommand {

    public ForwardCommand(List<ICommand> params) {
        super(params, true);
    }
}