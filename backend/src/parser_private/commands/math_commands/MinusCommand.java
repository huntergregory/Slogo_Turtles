package parser_private.commands.math_commands;

import parser_private.Command;
import java.util.List;

public class MinusCommand extends SingleParamMathCommand {

    public MinusCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return -myExpression.execute();
    }
}
