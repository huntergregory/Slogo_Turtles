package parser.ActionCommands;

import control.HomeCall;
import parser.ActionCommand;

public class HomeCommand extends ActionCommand {
    @Override
    public double execute() {
        return new HomeCall().call();
    }
}
