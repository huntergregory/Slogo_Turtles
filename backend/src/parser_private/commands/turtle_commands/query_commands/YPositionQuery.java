package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class YPositionQuery extends TurtleCommand {
    public YPositionQuery(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return myManager.getY();
    }
}

