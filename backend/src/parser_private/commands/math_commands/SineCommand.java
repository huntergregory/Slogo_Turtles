package parser_private.commands.math_commands;

import parser_private.Command;
import java.util.List;

public class SineCommand extends SingleParamMathCommand {

    public SineCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.sin(Math.toRadians(myExpression.execute()));
    }
}
