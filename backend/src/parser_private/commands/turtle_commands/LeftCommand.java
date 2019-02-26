package parser_private.commands.turtle_commands;

import external.ExternalAPICall;
import parser_private.Command;
import java.util.List;

public class LeftCommand extends Command {

    private Command myDegrees;
    private ExternalAPICall<Double, Double> myApiCall;

    public LeftCommand(List<Command> params, ExternalAPICall<Double, Double> apiCall) {
        super(params);
        this.myDegrees = params.get(0);
        this.myApiCall = apiCall;
    }

    @Override
    public double runCommand() {
        System.out.println("Turning left " + myDegrees.execute());
        return myApiCall.call(myDegrees.execute());
    }
}
