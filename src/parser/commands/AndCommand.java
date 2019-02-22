package parser.commands;

import parser.Command;
import java.util.List;

public class AndCommand extends TwoParamEvalCommand {

    public AndCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double execute() {
        return myExpression1.execute() && myExpression2.execute();
    }
}
