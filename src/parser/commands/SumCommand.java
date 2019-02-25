package parser.commands;

import parser.Command;

import java.util.List;

public class SumCommand extends TwoParamEvalCommand {

    public SumCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double execute() {
        return myExpression1.execute() + myExpression2.execute();
    }
}
