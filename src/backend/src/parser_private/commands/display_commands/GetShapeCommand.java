package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 * @author Harry Ross
 */
public class GetShapeCommand extends TurtleCommand {

    public GetShapeCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> turtle.getImageIndex());
    }
}
