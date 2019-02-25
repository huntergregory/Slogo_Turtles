package parser.commands;

import parser.Command;
import parser.EvalCommand;
import java.util.List;

abstract class TwoParamEvalCommand extends EvalCommand {

    Command myExpression1;
    Command myExpression2;

    TwoParamEvalCommand(List<Command> params) {
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }

    abstract public double execute();
}
