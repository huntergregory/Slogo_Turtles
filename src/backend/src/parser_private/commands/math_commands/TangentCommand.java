package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class TangentCommand extends SingleParamMathCommand {

    public TangentCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the tangent of an angle
     * @return The tangent of the given angle
     */
    public double execute() {
        return Math.tan(Math.toRadians(myExpression.execute()));
    }
}
