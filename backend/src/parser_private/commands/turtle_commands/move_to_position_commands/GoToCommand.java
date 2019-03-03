package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class GoToCommand extends MoveToPositionCommand {

    public GoToCommand(List<CommandInter> params) {
        super(params);
    }
}
