package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class NotCommand extends Command {

    private ICommand myExpression;

    public NotCommand(List<ICommand> params) {
        super(params);
        myExpression = params.get(0);
    }

    @Override
    public double execute() {
        return (myExpression.execute() == 0) ? 1 : 0;
    }
}
