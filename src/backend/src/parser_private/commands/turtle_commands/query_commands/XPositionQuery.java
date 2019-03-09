package parser_private.commands.turtle_commands.query_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class XPositionQuery extends TurtleCommand {
    public XPositionQuery(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> turtle.getPosition().getX());
    }
}

