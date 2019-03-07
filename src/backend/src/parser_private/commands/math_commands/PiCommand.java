package parser_private.commands.math_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class PiCommand extends Command {

    public PiCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.PI;
    }
}
