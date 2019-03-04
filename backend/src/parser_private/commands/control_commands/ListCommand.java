package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;
import java.util.function.Consumer;

public class ListCommand extends Command {

    public ListCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        for (int i = 0; i < mySubCommands.size() - 1; i++)
            mySubCommands.get(i).execute();
        return mySubCommands.get(mySubCommands.size() - 1).execute();
    }

    public CommandInter getParam(int index) {
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
