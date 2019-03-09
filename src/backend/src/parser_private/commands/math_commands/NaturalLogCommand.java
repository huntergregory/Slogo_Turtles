package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class NaturalLogCommand extends SingleParamMathCommand {

    public NaturalLogCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.log(myExpression.execute());
    }
}
