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

    public double execute() {
        return Math.atan(Math.toRadians(myExpression.execute()));
    }
}
