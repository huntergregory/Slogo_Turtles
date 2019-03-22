package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class RandomCommand extends SingleParamMathCommand {

    public RandomCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get a random number between 0 and the given parameter
     * @return The random number
     */
    public double execute() {
        return Math.random() * myExpression.execute();
    }
}
