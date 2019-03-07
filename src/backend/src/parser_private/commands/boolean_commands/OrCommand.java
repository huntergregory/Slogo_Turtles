package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class OrCommand extends AndCommand {

    private List<ICommand> myExpressions;

    public OrCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        super.makeOr();
        return super.execute();
    }
}
