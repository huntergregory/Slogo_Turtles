package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class SetPenSizeCommand extends TurtleCommand {

    private ICommand myPixels;

    public SetPenSizeCommand(List<ICommand> params) {
        super(params);
        myPixels = params.get(0);
    }

    /**
     * Set the pen size for each active turtle
     * @return The number of pixels
     */
    public double execute() {
        int pixels = (int)myPixels.execute();
        runTurtleCommand(turtle -> {
            turtle.getPen().setThickness(pixels);
            return 0.0;
        });
        return pixels;
    }
}
