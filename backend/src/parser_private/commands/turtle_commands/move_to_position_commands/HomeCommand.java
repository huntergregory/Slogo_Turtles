package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;

import java.util.List;

public class HomeCommand extends MoveToPositionCommand {

    public HomeCommand(List<Command> params) {
        super(params);
        this.myNewX = 0;
        this.myNewY = 0;
    }
}
