package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class YPositionQuery extends TurtleCommand {
    public YPositionQuery(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> -turtle.getPosition().getY());
    }
}

