package state_public;

import parser_private.Command;
import parser_private.StoredUserDefinedCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalCommands {

    private static GlobalCommands instance;
    private Map<String, StoredUserDefinedCommand> myStoredCommands;
    private Map<String, Integer> myParamCounts;

    private GlobalCommands() {
        this.myStoredCommands = new HashMap<>();
        this.myParamCounts = new HashMap<>();
    }

    public void addCommand(String commandName, StoredUserDefinedCommand newCommand) {
        myStoredCommands.put(commandName, newCommand);
        myParamCounts.put(commandName, newCommand.getArgumentCount());
    }

    int getParamCount(String command) {
        return myParamCounts.get(command);
    }

    boolean isDefined(String command) {
        return myStoredCommands.keySet().contains(command);
    }

    Command getCommand(String commandName, List<Command> params) {
        myStoredCommands.get(commandName).assignParams(params);
        return myStoredCommands.get(commandName);
    }
}
