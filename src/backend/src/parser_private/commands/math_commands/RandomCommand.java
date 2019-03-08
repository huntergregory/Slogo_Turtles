package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

public class RandomCommand extends SingleParamMathCommand {

    public RandomCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.random() * myExpression.execute();
    }
}
