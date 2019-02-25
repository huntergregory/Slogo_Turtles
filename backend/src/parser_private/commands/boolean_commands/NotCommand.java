package parser_private.commands.boolean_commands;

import parser_private.Command;
import parser_private.EvalCommand;

import java.util.List;

public class NotCommand extends EvalCommand {

    private Command myExpression;

    public NotCommand(List<Command> params) {
        super(params);
        myExpression = params.get(0);
    }

    @Override
    public double runCommand() {
        return (myExpression.execute() == 0) ? 1 : 0;
    }
}
