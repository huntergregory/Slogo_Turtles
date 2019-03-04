package parser_private.commands.control_commands;

import state_public.CommandInter;

import java.util.List;

public class RepeatCommand extends IterativeCommand {

    private CommandInter myTotalIter;

    public RepeatCommand(List<CommandInter> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";
        return iterate(countVarName, 1, limit, 1);
    }
}
