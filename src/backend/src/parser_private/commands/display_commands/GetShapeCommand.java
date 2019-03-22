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

    /**
     * Get the current shape
     * @return The index of the current shape
     */
    @Override
    public double execute() {
        return myStateManager.getTurtleManager().getImageIndex();
    }
}
