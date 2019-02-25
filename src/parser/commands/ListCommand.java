package parser.commands;

import parser.Command;

import java.util.List;

public class ListCommand extends Command {

    private List<Command> commands;

    public ListCommand(List<Command> params) {
        this.commands = params;
    }

    @Override
    public double execute() {
        for (int i = 0; i < commands.size() - 1; i++)
            commands.get(i).execute();
        return commands.get(commands.size() - 1).execute();
    }

}
