package external;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext<E extends Enum<E>> {

    private Map<E, ExternalAPICall<?,?>> externalAPICallMap;
    private boolean verified;

    public ExecutionContext() {
        externalAPICallMap = new HashMap<>();
        verified = false;
    }

    public void addExternalAPICall(E command, ExternalAPICall<?,?> externalAPICall) {
        externalAPICallMap.put(command, externalAPICall);
    }

    public boolean hasCommand(String commandName) {
        return externalAPICallMap.containsKey(commandName);
    }

    public ExternalAPICall<?,?> getExternalAPICall(E command) {
        return externalAPICallMap.get(command);
    }

}
