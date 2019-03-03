package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class XPositionQuery extends TurtleCommand {
    public XPositionQuery(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> turtle.getPosition().getX());
    }
}

