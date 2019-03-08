package parser_private.commands.math_commands;

import state.ICommand;

import java.util.List;

public class CosineCommand extends SingleParamMathCommand {

    public CosineCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return Math.cos(Math.toRadians(myExpression.execute()));
    }
}
