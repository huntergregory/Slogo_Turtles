package parser_private.commands.math_commands;

import parser_private.Command;

import java.util.List;

public class PiCommand extends Command {

    public PiCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.PI;
    }
}
