package parser_private.commands.turtle_commands;

import external.ExecutionContext;
import external.ExternalAPICall;
import parser_private.Command;
import parser_public.RequiredExternalAPICallsBack;

import java.util.List;

public class LeftCommand extends Command {

    private Command degreesCommand;
    private ExternalAPICall<Double, Double> apiCall;

    public LeftCommand(List<Command> params, ExecutionContext executionContext) {
        this.degreesCommand = params.get(0);
        this.apiCall = (ExternalAPICall<Double, Double>)executionContext.getExternalAPICall(RequiredExternalAPICallsBack.LEFT);
    }

    public double runCommand() {
        System.out.println("Turning left");
        return apiCall.call(degreesCommand.execute());
    }
}
