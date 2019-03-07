package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.ICommand;

import java.util.List;

public class SetPenSizeCommand extends TurtleCommand {

    private ICommand myPixels;

    public SetPenSizeCommand(List<ICommand> params) {
        super(params);
        myPixels = params.get(0);
    }

    public double execute() {
        int pixels = (int)myPixels.execute();
        runTurtleCommand(turtle -> {
            turtle.getPen().setThickness(pixels);
            return 0.0;
        });
        return pixels;
    }
}
