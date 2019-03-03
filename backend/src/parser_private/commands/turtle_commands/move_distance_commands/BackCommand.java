package parser_private.commands.turtle_commands.move_distance_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class BackCommand extends MoveDistanceCommand {
    public BackCommand(List<CommandInter> params) {
        super(params, false);
    }
}
