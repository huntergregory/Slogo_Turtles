package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class OrCommand extends Command {

    private List<CommandInter> myExpressions;

    public OrCommand(List<CommandInter> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        for (CommandInter exp : myExpressions) {
            if (exp.execute() == 1) {
                return 1;
            }
        }
        return 0;
    }
}
