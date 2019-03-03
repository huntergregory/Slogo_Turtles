package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;

import java.util.List;

public class ShowingQuery extends TurtleCommand {
    public ShowingQuery(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myManager.getIsShowing()) ? 1 : 0;
    }
}