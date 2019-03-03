package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class HideTurtleCommand extends TurtleCommand {

    public HideTurtleCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> {
            turtle.setShowing(false);
            return 0.0;
        });
    }
}
