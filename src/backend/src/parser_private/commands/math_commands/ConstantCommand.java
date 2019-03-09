package parser_private.commands.math_commands;

import parser_private.Command;

/**
 * @author David Miron
 */
public class ConstantCommand extends Command {

    private double value;

    public ConstantCommand(double value) {
        super(null);
        this.value = value;
    }

    @Override
    public double execute() {
        return this.value;
    }
}
