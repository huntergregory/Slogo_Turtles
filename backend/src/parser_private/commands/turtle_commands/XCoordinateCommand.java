package parser_private.commands.turtle_commands;

import external.ExternalAPICall;
import parser_private.Command;
import java.util.List;

public class XCoordinateCommand extends Command {

    private ExternalAPICall<Double, Void> myApiCall;

    public XCoordinateCommand(List<Command> params, ExternalAPICall<Double, Void> apiCall) {
        super(params);
        this.myApiCall = apiCall;
    }

    @Override
    public double runCommand() {
        return myApiCall.call(null);
    }
}
