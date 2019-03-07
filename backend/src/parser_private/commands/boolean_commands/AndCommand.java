package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class AndCommand extends Command {

    private List<ICommand> myExpressions;

    public AndCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        for (ICommand exp : myExpressions) {
            if (exp.execute() == 0) {
                return 0;
            }
        }
        return 1;
    }
}
