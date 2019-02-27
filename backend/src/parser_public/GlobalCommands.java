package parser_public;

import parser_private.Command;

import java.util.HashMap;
import java.util.Map;

public class GlobalCommands {

    private static GlobalCommands instance;
    private Map<String, Command> myVariables;

    private GlobalCommands() {
        this.myVariables = new HashMap<>();
    }

    public static GlobalCommands getInstance() {
        if (instance == null)
            instance = new GlobalCommands();
        return instance;
    }

    public void addCommand(String commandName, Command newCommand) {

    }

    boolean isDefined(String command) {
        return myVariables.keySet().contains(command);
    }
}
