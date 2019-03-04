package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class IfElseCommand extends Command {

    private CommandInter myExpression;
    private CommandInter myTrue;
    private CommandInter myFalse;

    public IfElseCommand(List<CommandInter> params) {
        super(params);
        myExpression = params.get(0);
        myTrue = params.get(1);
        myFalse = params.get(2);
    }

    @Override
    public double execute() {
        if (myExpression.execute() != 0) {
            return myTrue.execute();
        }
        return myFalse.execute();
    }
}
