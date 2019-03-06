package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class EqualCommand extends Command {

    private List<CommandInter> myExpressions;

    public EqualCommand(List<CommandInter> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double eq = myExpressions.get(0).execute();
        for (CommandInter exp : myExpressions) {
            if (exp.execute() != eq) {
                return 0;
            }
        }
        return 1;
    }
}
