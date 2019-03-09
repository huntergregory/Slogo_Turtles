package parser_private.commands.turtle_commands.rotate_angle_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class RightCommand extends RotateAngleCommand {

    public RightCommand(List<ICommand> params) {
        super(params, true);
    }
}
