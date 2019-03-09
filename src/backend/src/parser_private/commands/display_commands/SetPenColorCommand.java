package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class SetPenColorCommand extends TurtleCommand {

    private ICommand myIndex;

    public SetPenColorCommand(List<ICommand> params) {
        super(params);
        myIndex = params.get(0);
    }

    @Override
    public double execute() {
        int index = (int)myIndex.execute();
        runTurtleCommand(turtle -> {
            turtle.getPen().setColor(myStateManager.getPaletteManager().getPalette(index));
            return 0.0;
        });
        return index;
    }
}
