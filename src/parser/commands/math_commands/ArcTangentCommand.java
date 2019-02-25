package parser.commands.math_commands;

import parser.Command;
import java.util.List;

public class ArcTangentCommand extends SingleParamMathCommand {

    public ArcTangentCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.atan(Math.toRadians(myExpression.execute()));
    }
}
