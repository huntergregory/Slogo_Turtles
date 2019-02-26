package parser_private.commands.turtle_commands;

import parser_private.ActionCommand;
import parser_private.Command;
import java.util.List;

public class HomeCommand extends ActionCommand {

    public HomeCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        //return new HomeCall().call();
        return 0;
    }
}
