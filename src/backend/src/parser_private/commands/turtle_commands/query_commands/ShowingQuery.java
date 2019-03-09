package parser_private.commands.turtle_commands.query_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class ShowingQuery extends TurtleCommand {
    public ShowingQuery(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> turtle.getShowing() ? 1.0 : 0.0);
    }
}