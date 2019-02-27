package parser_private.commands.control_commands;

import parser_private.Command;
import java.util.List;
import java.util.function.Consumer;

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

    public void doForEachCommand(Consumer<Command> consumer) {

    }
}
