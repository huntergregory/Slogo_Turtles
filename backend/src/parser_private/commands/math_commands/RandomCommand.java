package parser_private.commands.math_commands;

import parser_private.Command;

import java.util.List;

public class RandomCommand extends SingleParamMathCommand {

    public RandomCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.random() * myExpression.execute();
    }
}
