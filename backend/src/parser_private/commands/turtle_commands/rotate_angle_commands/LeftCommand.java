package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class LeftCommand extends RotateAngleCommand {

    public LeftCommand(List<CommandInter> params) {
        super(params, false);
    }
}
