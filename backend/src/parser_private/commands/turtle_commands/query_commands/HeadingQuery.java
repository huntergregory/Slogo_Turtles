package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;

import java.util.List;

public class HeadingQuery extends TurtleCommand {
    public HeadingQuery(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return myManager.getHeading();
    }
}
