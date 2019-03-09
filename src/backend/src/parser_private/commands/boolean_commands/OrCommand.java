package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
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
