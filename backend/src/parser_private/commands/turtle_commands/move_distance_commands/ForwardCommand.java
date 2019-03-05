package parser_private.commands.turtle_commands.move_distance_commands;

import state_public.CommandInter;

import java.util.List;

public class ForwardCommand extends MoveDistanceCommand {

    public ForwardCommand(List<CommandInter> params) {
        super(params, true);
    }
}