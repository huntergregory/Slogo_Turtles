package parser_private.commands.math_commands;

import parser_private.Command;
import parser_private.EvalCommand;

import java.util.List;

public class PiCommand extends EvalCommand {

    public PiCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.PI;
    }
}
