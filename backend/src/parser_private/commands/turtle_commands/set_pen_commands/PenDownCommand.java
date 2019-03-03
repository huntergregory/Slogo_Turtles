package parser_private.commands.turtle_commands.set_pen_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class PenDownCommand extends TurtleCommand {

    public PenDownCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> {
            turtle.getPen().setIsDown(true);
            return 1.0;
        });

    }
}
