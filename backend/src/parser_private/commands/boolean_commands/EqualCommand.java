package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class EqualCommand extends Command {

    private List<ICommand> myExpressions;

    public EqualCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 0;
        }
        double eq = myExpressions.get(0).execute();
        for (ICommand exp : myExpressions) {
            if (exp.execute() != eq) {
                return 0;
            }
        }
        return 1;
    }
}
