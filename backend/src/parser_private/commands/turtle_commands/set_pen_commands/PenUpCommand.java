package parser_private.commands.turtle_commands.set_pen_commands;

import parser_private.Command;

import java.util.List;

public class PenUpCommand extends TurtleCommand {

    public PenUpCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        myManager.setPenDown(false);
        return 0;
    }
}
