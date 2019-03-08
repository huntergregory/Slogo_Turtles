package parser_private.commands.boolean_commands;

import state.ICommand;

import java.util.List;

public class NotEqualCommand extends EqualCommand {

    public NotEqualCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return (super.execute() == 0) ? 1 : 0;
    }
}
