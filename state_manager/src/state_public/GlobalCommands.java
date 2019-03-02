package state_public;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalCommands {

    private Map<String, UserDefinedCommandInter> myStoredCommands;
    private Map<String, Integer> myParamCounts;

    public GlobalCommands() {
        this.myStoredCommands = new HashMap<>();
        this.myParamCounts = new HashMap<>();
    }

    public void addCommand(String commandName, UserDefinedCommandInter newCommand) {
        myStoredCommands.put(commandName, newCommand);
        myParamCounts.put(commandName, newCommand.getArgumentCount());
    }

    int getParamCount(String command) {
        return myParamCounts.get(command);
    }

    boolean isDefined(String command) {
        return myStoredCommands.keySet().contains(command);
    }

    CommandInter getCommand(String commandName, List<CommandInter> params) {
        myStoredCommands.get(commandName).assignParams(params);
        return myStoredCommands.get(commandName);
    }
}
