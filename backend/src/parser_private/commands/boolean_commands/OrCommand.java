package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class OrCommand extends Command {

    private List<ICommand> myExpressions;

    public OrCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        for (ICommand exp : myExpressions) {
            if (exp.execute() == 1) {
                return 1;
            }
        }
        return 0;
    }
}
