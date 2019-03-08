package parser_private.commands.boolean_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

public class AndCommand extends Command {

    private List<ICommand> myExpressions;
    private int orToggle;

    public AndCommand(List<ICommand> params) {
        super(params);
        myExpressions = params;
        orToggle = 0;
    }

    void makeOr() {
        orToggle = 1;
    }

    @Override
    public double execute() {
        if (myExpressions.isEmpty()) {
            return 1;
        }
        for (ICommand exp : myExpressions) {
            if (exp.execute() == orToggle) {
                return orToggle;
            }
        }
        return (orToggle == 1) ? 0 : 1;
    }
}
