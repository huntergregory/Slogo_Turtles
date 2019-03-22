package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class ArcTangentCommand extends SingleParamMathCommand {

    public ArcTangentCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the arctangent of a value
     * @return the arctangent
     */
    public double execute() {
        return Math.atan(Math.toRadians(myExpression.execute()));
    }
}
