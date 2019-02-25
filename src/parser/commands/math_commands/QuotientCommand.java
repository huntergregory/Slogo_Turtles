package parser.commands.math_commands;

import parser.Command;
import java.util.List;

public class QuotientCommand extends TwoParamMathCommand {

    public QuotientCommand(List<Command> params) {
        super(params);
    }
    @Override
    public double runCommand() {
        return myExpression1.execute() / myExpression2.execute();
    }
}
