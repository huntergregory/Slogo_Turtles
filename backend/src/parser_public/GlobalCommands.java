package parser_public;

import parser_private.Command;
import parser_private.StoredUserDefinedCommand;
import parser_private.commands.control_commands.ListCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalCommands {

    private static GlobalCommands instance;
    private Map<String, StoredUserDefinedCommand> myStoredCommands;
    private Map<String, Integer> myParamCounts;
    private Map<String, List<StoredUserDefinedCommand>> myCreatedCommandInstances;

    private GlobalCommands() {
        this.myStoredCommands = new HashMap<>();
        this.myParamCounts = new HashMap<>();
        this.myCreatedCommandInstances = new HashMap<>();
    }

    public static GlobalCommands getInstance() {
        if (instance == null)
            instance = new GlobalCommands();
        return instance;
    }

    public void addCommand(String commandName, ListCommand args, ListCommand body) {//StoredUserDefinedCommand newCommand) {
        StoredUserDefinedCommand newCommand = new StoredUserDefinedCommand(args, body);
        myStoredCommands.put(commandName, newCommand); // Store or overwrite command type
        myParamCounts.put(commandName, newCommand.getArgumentCount()); // Store param count for new command type

        if (myCreatedCommandInstances.containsKey(commandName)) { // Propagate changes through existing references to this command
            for (StoredUserDefinedCommand command : myCreatedCommandInstances.get(commandName)) {
                command.updateArgsAndBody(args, body); // Do not need to worry about conflicting param numbers between old and new since undef. vars eval to 0
            }
        }
    }

    int getParamCount(String command) {
        return myParamCounts.get(command);
    }

    boolean isDefined(String command) {
        return myStoredCommands.keySet().contains(command);
    }

    Command getCommand(String commandName, List<Command> params) {
        StoredUserDefinedCommand newCommand = new StoredUserDefinedCommand(myStoredCommands.get(commandName));
        newCommand.assignParams(params);
        myCreatedCommandInstances.putIfAbsent(commandName, new ArrayList<>());
        myCreatedCommandInstances.get(commandName).add(newCommand);
        return newCommand;
    }
}
