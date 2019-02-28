package parser_private.commands.math_commands;

import parser_private.Command;

public class ConstantCommand extends Command {

    private double value;

    public ConstantCommand(double value) {
        super(null);
        this.value = value;
    }

    @Override
    public double runCommand() {
        return this.value;
    }
}
