package parser_private.commands.math_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class PiCommand extends Command {

    public PiCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get pi
     * @return pi
     */
    public double execute() {
        return Math.PI;
    }
}
