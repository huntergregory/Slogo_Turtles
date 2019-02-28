package parser_private.commands.turtle_commands.rotate_angle_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class RotateAngleCommand extends TurtleCommand {

    RotateAngleCommand(List<Command> params) {
        super(params);

    }

    @Override
    public double runCommand() {
        return 0;
    }
}
