package parser_private.commands.math_commands;

import state_public.ICommand;

import java.util.List;

public class TangentCommand extends SingleParamMathCommand {

    public TangentCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.tan(Math.toRadians(myExpression.execute()));
    }
}
