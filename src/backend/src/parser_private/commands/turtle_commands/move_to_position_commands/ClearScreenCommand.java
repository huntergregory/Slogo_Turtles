package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.commands.math_commands.ConstantCommand;
import state.ICommand;

import java.util.List;

/**
 * @author Harry Ross
 * @author David Miron
 */
public class ClearScreenCommand extends MoveToPositionCommand {

    public ClearScreenCommand(List<ICommand> params) {
        super(params);
        this.myNewX = new ConstantCommand(0.0);
        this.myNewY = new ConstantCommand(0.0);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            double rtn = super.execute();
            turtle.eraseLines();
            return rtn;
        });
    }
}
