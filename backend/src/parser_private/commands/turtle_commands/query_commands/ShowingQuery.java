package parser_private.commands.turtle_commands.query_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class ShowingQuery extends TurtleCommand {
    public ShowingQuery(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> turtle.getShowing() ? 1.0 : 0.0);
    }
}