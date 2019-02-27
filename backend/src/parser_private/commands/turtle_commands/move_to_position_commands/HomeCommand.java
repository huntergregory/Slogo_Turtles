package parser_private.commands.turtle_commands.move_to_position_commands;

import parser_private.Command;
import java.util.List;

public class HomeCommand extends Command {

    public HomeCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return 0.0; //new HomeCall().call();
    }
}
