package parser.commands.math_commands;

import parser.Command;
import java.util.List;

public class TangentCommand extends SingleParamMathCommand {

    public TangentCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.tan(Math.toRadians(myExpression.execute()));
    }
}
