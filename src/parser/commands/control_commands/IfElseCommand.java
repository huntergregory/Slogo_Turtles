package parser.commands.control_commands;

import parser.Command;
import parser.EvalCommand;
import java.util.List;

public class IfElseCommand extends EvalCommand {

    private Command myExpression;
    private Command myTrue;
    private Command myFalse;

    public IfElseCommand(List<Command> params) {
        super(params);
        myExpression = params.get(0);
        myTrue = params.get(1);
        myFalse = params.get(2);
    }

    @Override
    public double runCommand() {
        if (myExpression.execute() != 0) {
            return myTrue.execute();
        }
        return myFalse.execute();
    }
}
