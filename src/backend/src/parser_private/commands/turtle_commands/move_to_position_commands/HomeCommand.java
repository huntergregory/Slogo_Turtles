package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.commands.math_commands.ConstantCommand;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 */
public class HomeCommand extends MoveToPositionCommand {

    public HomeCommand(List<ICommand> params) {
        super(params);
        this.myNewX = new ConstantCommand(0.0);
        this.myNewY = new ConstantCommand(0.0);
    }
}
