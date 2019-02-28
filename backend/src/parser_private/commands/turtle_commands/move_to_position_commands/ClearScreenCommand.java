package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import parser_private.commands.math_commands.ConstantCommand;

import java.util.List;

public class ClearScreenCommand extends MoveToPositionCommand {

    public ClearScreenCommand(List<Command> params) {
        super(params);
        this.myNewX = new ConstantCommand(0.0);
        this.myNewY = new ConstantCommand(0.0);
    }

    @Override
    public double runCommand() {
        double rtn = super.runCommand();
        myManager.eraseLines();
        return rtn;
    }
}
