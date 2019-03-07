package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state_public.ICommand;

import java.util.List;

public class ShowTurtleCommand extends TurtleCommand {

    public ShowTurtleCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            turtle.setShowing(true);
            return 1.0;
        });
    }
}
