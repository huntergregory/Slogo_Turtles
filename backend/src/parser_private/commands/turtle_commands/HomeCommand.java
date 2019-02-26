package parser_private.commands.turtle_commands;

import external.ExternalAPICall;
import parser_private.Command;
import java.util.List;

public class HomeCommand extends Command {

    private ExternalAPICall<Double, Double> myApiCall;

    public HomeCommand(List<Command> params, ExternalAPICall<Double, Double> apiCall) {
        super(params);
        this.myApiCall = apiCall;
    }

    @Override
    public double runCommand() {
        return myApiCall.call(null);
    }
}
