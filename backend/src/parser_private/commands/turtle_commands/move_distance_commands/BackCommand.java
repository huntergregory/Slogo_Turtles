package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;

import java.util.List;

public class BackCommand extends MoveDistanceCommand {
    public BackCommand(List<Command> params) {
        super(params, false);
    }
}
