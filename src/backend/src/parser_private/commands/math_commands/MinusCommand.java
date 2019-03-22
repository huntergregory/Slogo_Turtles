package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class MinusCommand extends SingleParamMathCommand {

    public MinusCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the negative of a number
     * @return The negative of the number
     */
    public double execute() {
        return -myExpression.execute();
    }
}
