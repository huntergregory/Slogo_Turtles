package parser_private.commands.control_commands;

import parser_private.Command;
import state.ICommand;

import java.util.List;

/**
 * Command that stores and executes other commands within itself as a list
 * @author Harry Ross
 */
public class ListCommand extends Command {

    /**
     * Creates new List command, assigns parameters
     * @param params contents of list to be assigned
     */
    public ListCommand(List<ICommand> params) {
        super(params);
    }

    /**
     * Executes List command
     * @return output of final member of parameters
     */
    @Override
    public double execute() {
        for (int i = 0; i < mySubCommands.size() - 1; i++)
            mySubCommands.get(i).execute();
        return mySubCommands.get(mySubCommands.size() - 1).execute();
    }

    /**
     * Returns Command object for requested parameter index
     * @param index Index of requested parameter
     * @return Command object corresponding with index specified
     */
    @Override
    public ICommand getParam(int index) {
        return mySubCommands.get(index);
    }

    /**
     * Returns size of parameter list as integer
     * @return size of parameter list
     */
    @Override
    public int size() {
        return mySubCommands.size();
    }

    boolean isEmpty() {
        return mySubCommands.size() == 0;
    }
}
