package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class ShowTurtleCall extends TurtleCommand {

    public ShowTurtleCall(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        myManager.setIsShowing(true);
        return 1;
    }
}
