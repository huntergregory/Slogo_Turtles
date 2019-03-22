package parser_private.commands.math_commands;

import parser_private.Command;

/**
 * Class to represent a constant
 * @author David Miron
 */
public class ConstantCommand extends Command {

    private double value;

    public ConstantCommand(double value) {
        super(null);
        this.value = value;
    }

    /**
     * Get the value of the constant
     * @return The value of the constant
     */
    @Override
    public double execute() {
        return this.value;
    }
}
