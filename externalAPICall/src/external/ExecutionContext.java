package external;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {

    private Map<String, ExternalAPICall<?,?>> externalAPICallMap;

    public ExecutionContext() {
        externalAPICallMap = new HashMap<>();
    }

    public void addExternalAPICall(String commandName, ExternalAPICall<?,?> externalAPICall) {
        externalAPICallMap.put(commandName, externalAPICall);
    }

    public boolean hasCommand(String commandName) {
        return externalAPICallMap.containsKey(commandName);
    }

    public ExternalAPICall<?,?> getExternalAPICall(String commandName) {
        return externalAPICallMap.get(commandName);
    }

}
