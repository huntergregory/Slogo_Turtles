package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;

import java.util.List;

public class ClearScreenCommand extends MoveToPositionCommand {

    public ClearScreenCommand(List<Command> params) {
        super(params);
        this.myNewX = 0;
        this.myNewY = 0;
    }

    @Override
    public double runCommand() {
        myManager.eraseLines();
        return super.runCommand();
    }
}
