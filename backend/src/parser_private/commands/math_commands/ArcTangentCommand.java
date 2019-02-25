package parser_private.commands.math_commands;

import parser_private.Command;

import java.util.List;

public class ArcTangentCommand extends SingleParamMathCommand {

    public ArcTangentCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.atan(Math.toRadians(myExpression.execute()));
    }
}
