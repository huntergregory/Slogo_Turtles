package parser_private.commands.turtle_commands;

import external.ExternalAPICall;
import parser_private.Command;

import java.util.List;

public class XCoordinateCommand extends Command {

    private ExternalAPICall<Double, Void> apiCall;

    public XCoordinateCommand(List<Command> params, ExternalAPICall<Double, Void> apiCall) {
        this.apiCall = apiCall;
    }

    public double runCommand() {
        return apiCall.call(null);
    }

}
