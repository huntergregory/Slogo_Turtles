package parser.commands.math_commands;

import parser.Command;
import parser.EvalCommand;
import java.util.List;

abstract class TwoParamMathCommand extends EvalCommand {

    Command myExpression1;
    Command myExpression2;

    TwoParamMathCommand(List<Command> params) {
        super(params);
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }
}
