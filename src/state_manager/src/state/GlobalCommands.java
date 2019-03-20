package state;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that maintains a set of user defined commands within a given workspace.
 * Ex. GlobalCommands glcmd = new GlobalCommands();
 *     glcmd.addCommand("dothis", args, body, cmd);
 * @author Harry Ross
 */
public class GlobalCommands {

    private Map<String, IUserCommand> myStoredCommands;
    private Map<IUserCommand, ICommand> myStoredArgs;
    private Map<IUserCommand, ICommand> myStoredBodies;
    private Map<String, Integer> myParamCounts;
    private Map<String, List<IUserCommand>> myCreatedCommandInstances;
    private ListProperty<String> myCommandsProperty;

    /**
     * Creates new GlobalCommands object, initializes instance variables
     */
    public GlobalCommands() {
        this.myStoredCommands = new HashMap<>();
        this.myStoredArgs = new HashMap<>();
        this.myStoredBodies = new HashMap<>();
        this.myParamCounts = new HashMap<>();
        this.myCreatedCommandInstances = new HashMap<>();
        myCommandsProperty = new SimpleListProperty<>();
        myCommandsProperty.set(FXCollections.observableArrayList());
    }

    /**
     * Adds new user defined command to this GlobalCommands object
     * @param commandName String name of new command
     * @param args Argument(s) of new command
     * @param body Body of new command (what executes)
     * @param newCommand Placeholder Command object that must be passed in since a new UserCommand cannot be created
     *                   from within this object (API limitation).
     */
    public void addCommand(String commandName, ICommand args, ICommand body, IUserCommand newCommand) { // Gets myArguments, myBody from ToCommand
        myStoredCommands.put(commandName, newCommand);
        myStoredArgs.put(newCommand, args); // Store or overwrite command type
        myStoredBodies.put(newCommand, body);
        myParamCounts.put(commandName, args.size()); // Store param count for new command type
        addToObservableList(commandName);

        if (myCreatedCommandInstances.containsKey(commandName)) { // Propagate changes through existing references to this command
            for (IUserCommand command : myCreatedCommandInstances.get(commandName)) {
                command.applyArgsAndBody(args, body); // Do not need to worry about conflicting param numbers between old and new since undef. vars eval to 0
            }
        }
    }

    private void addToObservableList(String command) {
        ObservableList<String> observableList = myCommandsProperty.getValue();
        observableList.add(command);
        myCommandsProperty.set(observableList);
    }

    /**
     * Returns number of parameters for a given user defined command
     * @param command String name of command to be checked
     * @return Number of parameters as integer
     */
    public int getParamCount(String command) {
        return myParamCounts.get(command);
    }

    /**
     * Returns if a given user defined command has been defined in the scope of this workspace
     * @param command String name of command to be checked
     * @return True if command has been defined in this scope
     */
    public boolean isDefined(String command) {
        return myStoredCommands.keySet().contains(command);
    }

    /**
     * Creates and returns new instance of requested user defined command
     * @param commandName String name of requested user defined command
     * @param params List of parameters to be assigned to new command instance
     * @return New instance of user defined command with given parameters
     */
    public ICommand getCommand(String commandName, List<ICommand> params) {
        IUserCommand newCommand = myStoredCommands.get(commandName).getNewInstance(); // Will return UDC with null args and body
        newCommand.applyArgsAndBody(myStoredArgs.get(myStoredCommands.get(commandName)),
                myStoredBodies.get(myStoredCommands.get(commandName))); // Get stored args and body

        newCommand.assignParams(params);
        myCreatedCommandInstances.putIfAbsent(commandName, new ArrayList<>());
        myCreatedCommandInstances.get(commandName).add(newCommand);
        return newCommand;
    }

    /**
     * Returns ListProperty of defined user commands for a workspace
     * @return ListProperty of user defined commands
     */
    public ListProperty getCommandsList() {
        return myCommandsProperty;
    }
}
