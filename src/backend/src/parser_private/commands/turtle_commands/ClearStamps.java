package parser_private.commands.turtle_commands;

import state.ICommand;

import java.util.List;

public class ClearStamps extends TurtleCommand {

    public ClearStamps(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return 0;
    }
}
