package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class NotEqualCommand extends Command {

    private List<CommandInter> myExpressions;

    public NotEqualCommand(List<CommandInter> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        double eq = myExpressions.get(0).execute();
        for (CommandInter exp : myExpressions) {
            if (exp.execute() != eq) {
                return 1;
            }
        }
        return 0;
    }
}
