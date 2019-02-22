package parser.commands;

import parser.Command;
import java.util.List;

public class OrCommand extends TwoParamEvalCommand {

    public OrCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double execute() {
        return (myExpression1.execute() != 0 || myExpression2.execute() != 0) ? 1 : 0;
    }
}
