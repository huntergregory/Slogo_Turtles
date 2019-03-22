package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class SineCommand extends SingleParamMathCommand {

    public SineCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Get the sin of an angle
     * @return The sin of an angle
     */
    public double execute() {
        return Math.sin(Math.toRadians(myExpression.execute()));
    }
}
