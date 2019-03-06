package parser_private.commands.turtle_commands.rotate_angle_commands;

import state_public.ICommand;

import java.util.List;

public class RightCommand extends RotateAngleCommand {

    public RightCommand(List<ICommand> params) {
        super(params, true);
    }
}
