package parser_private.commands.turtle_commands.rotate_angle_commands;

import state_public.CommandInter;

import java.util.List;

public class RightCommand extends RotateAngleCommand {

    public RightCommand(List<CommandInter> params) {
        super(params, true);
    }
}
