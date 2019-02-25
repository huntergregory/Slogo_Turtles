package parser_private.commands.math_commands;

import parser_private.Command;

import java.util.List;

public class TangentCommand extends SingleParamMathCommand {

    public TangentCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.tan(Math.toRadians(myExpression.execute()));
    }
}
