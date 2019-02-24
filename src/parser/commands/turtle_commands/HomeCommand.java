package parser.commands.turtle_commands;

import control.frontendapi.move_to_position_calls.HomeCall;
import parser.ActionCommand;

public class HomeCommand extends ActionCommand {
    @Override
    public double runCommand() {
        return new HomeCall().call();
    }
}
