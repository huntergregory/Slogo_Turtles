package parser_private.commands.turtle_commands.query_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.ICommand;

import java.util.List;

public class XPositionQuery extends TurtleCommand {
    public XPositionQuery(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> turtle.getPosition().getX());
    }
}

