package parser_private.commands.math_commands;

import parser_private.Command;
import java.util.List;

public class CosineCommand extends SingleParamMathCommand {

    public CosineCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.cos(Math.toRadians(myExpression.execute()));
    }
}
