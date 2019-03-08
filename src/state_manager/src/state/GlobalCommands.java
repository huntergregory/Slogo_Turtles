package state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalCommands {

    private Map<String, IUserCommand> myStoredCommands;
    private Map<IUserCommand, ICommand> myStoredArgs;
    private Map<IUserCommand, ICommand> myStoredBodies;
    private Map<String, Integer> myParamCounts;
    private Map<String, List<IUserCommand>> myCreatedCommandInstances;

    public GlobalCommands() {
        this.myStoredCommands = new HashMap<>();
        this.myStoredArgs = new HashMap<>();
        this.myStoredBodies = new HashMap<>();
        this.myParamCounts = new HashMap<>();
        this.myCreatedCommandInstances = new HashMap<>();
    }

    public void addCommand(String commandName, ICommand args, ICommand body, IUserCommand newCommand) { // Gets myArguments, myBody from ToCommand
        myStoredCommands.put(commandName, newCommand);
        myStoredArgs.put(newCommand, args); // Store or overwrite command type
        myStoredBodies.put(newCommand, body);
        myParamCounts.put(commandName, args.size()); // Store param count for new command type

        if (myCreatedCommandInstances.containsKey(commandName)) { // Propagate changes through existing references to this command
            for (IUserCommand command : myCreatedCommandInstances.get(commandName)) {
                command.applyArgsAndBody(args, body); // Do not need to worry about conflicting param numbers between old and new since undef. vars eval to 0
            }
        }
    }

    public int getParamCount(String command) {
        return myParamCounts.get(command);
    }

    public boolean isDefined(String command) {
        return myStoredCommands.keySet().contains(command);
    }

    public ICommand getCommand(String commandName, List<ICommand> params) {
        IUserCommand newCommand = myStoredCommands.get(commandName).getNewInstance(); // Will return UDC with null args and body
        newCommand.applyArgsAndBody(myStoredArgs.get(myStoredCommands.get(commandName)),
                myStoredBodies.get(myStoredCommands.get(commandName))); // Get stored args and body

        newCommand.assignParams(params);
        myCreatedCommandInstances.putIfAbsent(commandName, new ArrayList<>());
        myCreatedCommandInstances.get(commandName).add(newCommand);
        return newCommand;
    }
}