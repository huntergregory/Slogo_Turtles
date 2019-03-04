package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.CommandInter;

import java.util.List;

public class ShowTurtleCommand extends TurtleCommand {

    public ShowTurtleCommand(List<CommandInter> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return runTurtleCommand((turtle) -> {
            turtle.setShowing(true);
            return 1.0;
        });
    }
}
