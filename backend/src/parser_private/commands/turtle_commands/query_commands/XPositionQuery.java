package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;

import java.util.List;

public class XPositionQuery extends TurtleCommand {
    public XPositionQuery(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return myManager.getX();
    }
}

