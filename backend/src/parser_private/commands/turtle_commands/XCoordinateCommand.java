package parser_private.commands.turtle_commands;

import external.ExecutionContext;
import external.ExternalAPICall;
import parser_private.Command;
import parser_public.RequiredExternalAPICallsBack;

import java.util.List;

public class XCoordinateCommand extends Command {

    private ExternalAPICall<Double, Void> myApiCall;

    public XCoordinateCommand(List<Command> params, ExecutionContext executionContext) {
        super(params);
        this.myApiCall = executionContext.getExternalAPICall(RequiredExternalAPICallsBack.XCOR);
    }

    @Override
    public double runCommand() {
        return myApiCall.call(null);
    }
}
