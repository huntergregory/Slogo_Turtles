package parser_private.commands.turtle_commands.set_pen_commands;

import parser_private.Command;

import java.util.List;

public class PenDownCommand extends TurtleCommand {

    public PenDownCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        myManager.setPenDown(true);
        return 1;
    }
}
