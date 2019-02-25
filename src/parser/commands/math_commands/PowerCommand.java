package parser.commands.math_commands;

import parser.Command;
import java.util.List;

public class PowerCommand extends TwoParamMathCommand {

    public PowerCommand(List<Command> params) {
        super(params);
    }

    public double runCommand() {
        return Math.pow(myExpression1.execute(), myExpression2.execute());
    }
}
