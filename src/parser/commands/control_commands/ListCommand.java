package parser.commands.control_commands;

import parser.Command;
import java.util.List;

public class ListCommand extends Command {

    public ListCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        for (int i = 0; i < mySubCommands.size() - 1; i++)
            mySubCommands.get(i).execute();
        return mySubCommands.get(mySubCommands.size() - 1).execute();
    }

    Command getParam(int index) {
        return mySubCommands.get(index);
    }
}
