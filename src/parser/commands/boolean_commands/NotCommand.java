package parser.commands.boolean_commands;

import parser.Command;
import parser.EvalCommand;

import java.util.List;

public class NotCommand extends EvalCommand {

    private Command myExpression;

    public NotCommand(List<Command> params) {
        myExpression = params.get(0);
    }

    @Override
    public double runCommand() {
        return (myExpression.execute() == 0) ? 1 : 0;
    }
}
