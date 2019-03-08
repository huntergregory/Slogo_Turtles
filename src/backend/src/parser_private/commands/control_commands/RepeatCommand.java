package parser_private.commands.control_commands;

import state.ICommand;

import java.util.List;

public class RepeatCommand extends IterativeCommand {

    private ICommand myTotalIter;

    public RepeatCommand(List<ICommand> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double execute() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";
        return iterate(countVarName, 1, limit, 1);
    }
}
