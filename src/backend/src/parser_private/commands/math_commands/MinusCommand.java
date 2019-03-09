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

    public double execute() {
        return -myExpression.execute();
    }
}
