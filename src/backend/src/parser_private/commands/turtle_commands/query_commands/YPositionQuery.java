package parser_private.commands.turtle_commands.query_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

/**
 * @author David Miron
 */
public class YPositionQuery extends TurtleCommand {
    public YPositionQuery(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> -turtle.getPosition().getY());
    }
}

