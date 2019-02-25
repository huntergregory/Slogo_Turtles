package parser.commands.math_commands;

import parser.Command;
import java.util.List;

public class SineCommand extends SingleParamMathCommand {

    public SineCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.sin(myExpression.execute());
    }
}
