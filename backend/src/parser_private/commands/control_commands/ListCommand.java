package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

public class ListCommand extends Command {

    public ListCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        for (int i = 0; i < mySubCommands.size() - 1; i++)
            mySubCommands.get(i).execute();
        return mySubCommands.get(mySubCommands.size() - 1).execute();
    }

    @Override
    public ICommand getParam(int index) {
        return mySubCommands.get(index);
    }

    @Override
    public int size() {
        return mySubCommands.size();
    }

    boolean isEmpty() {
        return mySubCommands.size() == 0;
    }
}
