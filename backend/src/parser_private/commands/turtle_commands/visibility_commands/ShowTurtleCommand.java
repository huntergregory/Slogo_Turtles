package parser_private.commands.turtle_commands.visibility_commands;

import parser_private.Command;
import parser_private.commands.turtle_commands.TurtleCommand;

import java.util.List;

public class ShowTurtleCommand extends TurtleCommand {

    public ShowTurtleCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        System.out.println("showing");
        myManager.setIsShowing(true);
        return 1;
    }
}
