package parser_public;

import parser_private.StoredUserDefinedCommand;
import parser_private.commands.control_commands.ListCommand;

import java.util.HashMap;
import java.util.Map;

public class GlobalCommands {

    private static GlobalCommands instance;
    private Map<String, StoredUserDefinedCommand> myCommands;

    private GlobalCommands() {
        this.myCommands = new HashMap<>();
    }

    public static GlobalCommands getInstance() {
        if (instance == null)
            instance = new GlobalCommands();
        return instance;
    }

    public void addCommand(String commandName, StoredUserDefinedCommand newCommand) {
        myCommands.put(commandName, newCommand);
    }

    boolean isDefined(String command) {
        return myCommands.keySet().contains(command);
    }

    public double runCommand(String commandName, ListCommand params) {
        myCommands.get(commandName).run(params);
    }
}
