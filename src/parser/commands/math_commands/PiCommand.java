package parser.commands.math_commands;

import parser.Command;
import parser.EvalCommand;

import java.util.List;

public class PiCommand extends EvalCommand {

    public PiCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.PI;
    }
}
