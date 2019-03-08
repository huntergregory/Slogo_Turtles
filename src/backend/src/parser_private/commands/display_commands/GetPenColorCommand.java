package parser_private.commands.display_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.ICommand;

import java.util.List;

public class GetPenColorCommand extends TurtleCommand {

    public GetPenColorCommand(List<ICommand> params) {
        super(params);
    }

    public double execute() {
        return runTurtleCommand(turtle -> (double)turtle.getPen().getColor().getId());
    }
}