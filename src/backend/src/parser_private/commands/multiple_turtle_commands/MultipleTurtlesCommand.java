package parser_private.commands.multiple_turtle_commands;

import parser_private.Command;
import parser_private.commands.control_commands.ListCommand;
import state.ICommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold functionality of running commands for multiple turtles
 * @author David Miron
 */
public abstract class MultipleTurtlesCommand extends Command {

    public MultipleTurtlesCommand(List<ICommand> params) {
        super(params);
    }

    private double tell(List<Integer> turtleIDs) {
        myStateManager.getTurtleManager().setTurtlesActive(turtleIDs);
        return turtleIDs.get(turtleIDs.size() - 1);
    }

    private List<Integer> getIDsFromListCommand(ListCommand commands) {
        double turtleID = 0;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            turtleID = commands.getParam(i).execute();
            ids.add((int)turtleID);
        }
        return ids;
    }

    protected double tell(ListCommand turtles) {
        return tell(getIDsFromListCommand(turtles));
    }

    protected double ask(ListCommand turtles, ListCommand commands) {
        return ask(getIDsFromListCommand(turtles), commands);
    }

    protected double ask(List<Integer> turtleIDs, ListCommand commands) {
        tell(turtleIDs);
        double output = commands.execute();
        myStateManager.getTurtleManager().revertActiveTurtles();
        return output;
    }

}
