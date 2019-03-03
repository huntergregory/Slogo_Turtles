package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;

import java.util.List;

public class PenDownQuery extends TurtleCommand {
    public PenDownQuery(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myManager.getPenDown()) ? 1 : 0;
    }
}
