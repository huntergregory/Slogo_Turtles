package parser.commands.math_commands;

import parser.EvalCommand;

public class ConstantCommand extends EvalCommand {

    private double value;

    public ConstantCommand(double value) {
        this.value = value;
    }

    @Override
    public double runCommand() {
        return this.value;
    }

}
