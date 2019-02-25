package parser_private.commands.math_commands;

import parser_private.EvalCommand;

public class ConstantCommand extends EvalCommand {

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
