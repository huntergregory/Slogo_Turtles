package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.commands.turtle_commands.TurtleCommand;
import state.ICommand;

import java.util.List;

public class HideTurtleCommand extends TurtleCommand {

    public HideTurtleCommand(List<ICommand> params) {
        super(params);
    }

    @Override
    public double execute() {
        return runTurtleCommand((turtle) -> {
            turtle.setShowing(false);
            return 0.0;
        });
    }
}
