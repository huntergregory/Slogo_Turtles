package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class CosineCommand extends SingleParamMathCommand {

    public CosineCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the cosine of an angle
     * @return The cosine of the angle
     */
    public double execute() {
        return Math.cos(Math.toRadians(myExpression.execute()));
    }
}
