package parser_private.commands.display_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class SetPaletteCommand extends Command {

    private ICommand myIndex;
    private ICommand myRed;
    private ICommand myGreen;
    private ICommand myBlue;

    public SetPaletteCommand(List<ICommand> params) {
        super(params);
        myIndex = params.get(0);
        myRed = params.get(1);
        myGreen = params.get(2);
        myBlue = params.get(3);
    }

    /**
     * Set the values for a palette
     * @return The index of the palette set
     */
    @Override
    public double execute() {
        int index = (int)myIndex.execute();
        myStateManager.getPaletteManager().setPalette(index, (int)myRed.execute(),
                (int)myGreen.execute(), (int)myBlue.execute());
        return index;
    }
}
