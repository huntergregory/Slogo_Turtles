package parser_private.commands.turtle_commands.set_pen_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.ICommand;

import java.util.List;

public class PenUpCommand extends TurtleCommand {

    public PenUpCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            turtle.getPen().setIsDown(false);
            return 0.0;
        });
    }
}
