package parser_private.commands.turtle_commands;

import external.ExternalAPICall;
import parser_private.Command;
import java.util.List;

public class ForwardCommand extends Command {

    private Command myDistance;
    private ExternalAPICall<Double, Double> myApiCall;

    public ForwardCommand(List<Command> params, ExternalAPICall<Double, Double> apiCall) {
        super(params);
        this.myDistance = params.get(0);
        this.myApiCall = apiCall;
    }

    @Override
    public double runCommand() {
        System.out.println("Executing Forward with distance " + myDistance.execute());
        return myApiCall.call(myDistance.execute());
    }
}
