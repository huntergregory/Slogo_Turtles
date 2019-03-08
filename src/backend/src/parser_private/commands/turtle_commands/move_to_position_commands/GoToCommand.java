package parser_private.commands.turtle_commands.move_to_position_commands;

import state.ICommand;

import java.util.List;

public class GoToCommand extends MoveToPositionCommand {

    public GoToCommand(List<ICommand> params) {
        super(params);
    }
}
