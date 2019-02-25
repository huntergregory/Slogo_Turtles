package parser_private.commands.math_commands;

import parser_private.Command;
import java.util.List;

public class NaturalLogCommand extends SingleParamMathCommand {

    public NaturalLogCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.log(myExpression.execute());
    }
}
