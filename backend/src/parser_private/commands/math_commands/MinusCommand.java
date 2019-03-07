package parser_private.commands.math_commands;

import state_public.ICommand;

import java.util.List;

public class MinusCommand extends SingleParamMathCommand {

    public MinusCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return -myExpression.execute();
    }
}
