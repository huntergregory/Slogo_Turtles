package parser_private.commands.display_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

public class SetBackgroundCommand extends Command {

    ICommand myIndex;

    public SetBackgroundCommand(List<ICommand> params) {
        super(params);
        myIndex = params.get(0);
    }

    public double execute() {
        int index = (int)myIndex.execute();
        myStateManager.setBackgroundColor(index);
        return index;
    }

}
