package parser_private.commands.turtle_commands;

import external.ExecutionContext;
import external.ExternalAPICall;
import parser_private.Command;
import parser_public.RequiredExternalAPICallsBack;

import java.util.List;

public class HomeCommand extends Command {

    private ExternalAPICall<Double, Void> myApiCall;

    public HomeCommand(List<Command> params, ExecutionContext executionContext) {
        super(params);
        this.myApiCall = executionContext.getExternalAPICall(RequiredExternalAPICallsBack.HOME);
    }

    @Override
    public double runCommand() {
        return myApiCall.call(null);
    }
}
