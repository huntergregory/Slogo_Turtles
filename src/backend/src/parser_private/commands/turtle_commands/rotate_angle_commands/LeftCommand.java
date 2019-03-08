package parser_private.commands.turtle_commands.rotate_angle_commands;

import state.ICommand;

import java.util.List;

public class LeftCommand extends RotateAngleCommand {

    public LeftCommand(List<ICommand> params) {
        super(params, false);
    }
}
