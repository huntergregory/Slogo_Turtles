package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;

import java.util.List;

public class RightCommand extends RotateAngleCommand {

    public RightCommand(List<Command> params) {
        super(params, true);
    }
}
