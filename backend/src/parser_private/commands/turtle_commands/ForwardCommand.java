package parser_private.commands.turtle_commands;

import external.ExecutionContext;
import external.ExternalAPICall;
import parser_private.ActionCommand;
import parser_private.Command;
import parser_public.RequiredExternalAPICallsBack;

import java.util.List;

public class ForwardCommand extends ActionCommand {

    private Command myDistance;
    private ExternalAPICall<Double, Double> apiCall;

    public ForwardCommand(List<Command> params, ExecutionContext<RequiredExternalAPICallsBack> exernalAPI) {
        super(params);
        this.myDistance = params.get(0);
        this.apiCall = (ExternalAPICall<Double, Double>) exernalAPI.getExternalAPICall(RequiredExternalAPICallsBack.FORWARD);
    }

    @Override
    public double runCommand() {
        System.out.println("Executing Forward with distance " + myDistance.execute());
        return apiCall.call(myDistance.execute());
    }
}
