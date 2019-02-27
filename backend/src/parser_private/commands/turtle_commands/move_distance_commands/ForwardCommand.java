package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;

import java.util.List;

public class ForwardCommand extends MoveDistanceCommand {
    public ForwardCommand(List<Command> params) {
        super(params, true);
    }
}