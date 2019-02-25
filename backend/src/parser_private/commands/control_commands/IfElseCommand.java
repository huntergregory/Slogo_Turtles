package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.EvalCommand;

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
