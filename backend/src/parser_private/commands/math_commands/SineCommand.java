package parser_private.commands.math_commands;

import state_public.ICommand;

import java.util.List;

public class SineCommand extends SingleParamMathCommand {

    public SineCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.sin(Math.toRadians(myExpression.execute()));
    }
}
