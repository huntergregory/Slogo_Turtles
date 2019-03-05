package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class NotCommand extends Command {

    private CommandInter myExpression;

    public NotCommand(List<CommandInter> params) {
        super(params);
        myExpression = params.get(0);
    }

    @Override
    public double execute() {
        return (myExpression.execute() == 0) ? 1 : 0;
    }
}
