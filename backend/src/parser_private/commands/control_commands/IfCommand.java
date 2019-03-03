package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class IfCommand extends Command {

    private CommandInter myExpression;
    private CommandInter myBody;

    public IfCommand(List<CommandInter> params) {
        super(params);
        myExpression = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        if (myExpression.execute() != 0) {
            return myBody.execute();
        }
        return 0;
    }
}
