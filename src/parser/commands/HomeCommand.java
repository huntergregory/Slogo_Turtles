package parser.commands;

import control.frontendapi.HomeCall;
import parser.ActionCommand;

public class HomeCommand extends ActionCommand {
    @Override
    public double execute() {
        return new HomeCall().call();
    }
}
