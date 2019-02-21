package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CommandParser {

    private Map<String, Integer> myCommandMap; // Assume this is initialized properly with command names -> num of params
    private Queue<Command> myCommandQueue;

    // Loops until overall input is empty
    public void parse(String program) {
        // Account for more than one space between terms
        program = program.trim();
        // Check if empty input (above call necessary in case all whitespace)
        if (program.isEmpty()) {
            throw new ParserException("Empty input string!");
        }
        while (!program.trim().isEmpty()) {
            Command nextCommand = makeCommand(program); // Get next command
            // Add to commandQueue
            myCommandQueue.add(nextCommand);
        }
    }

    // Loops until individual command hierarchy is satisfied
    private Command makeCommand(String input) {
        // Account for more than one space between terms
        input = input.trim();
        // Isolate next chunk
        String chunk = input.split(" ")[0];
        // Check if a number, if so, return
        if (isNumeric(chunk)) {
            return new EvalCommand("VAL", Double.parseDouble(chunk));
        } // Creates dummy VAL command that acts as container for a number (execute() returns its double)
        // This VAL type can act as the model for user-defined variables potentially
        // Else, check if valid Command type
        if (!myCommandMap.keySet().contains(chunk)) {
            throw new ParserException("Invalid command!");
        }
        // If valid, check parameter count
        int numParams = myCommandMap.get(chunk);
        // Get parameters, add to list
        List<Command> paramList = new ArrayList<>(numParams);
        for (int i = 0; i < numParams; i++) { // Recursively identify parameters
            input = input.substring(input.indexOf(" ") + 1); // Remove processed chunk
            paramList.set(i, makeCommand(input));
        }
        input = input.substring(input.indexOf(" ") + 1); // Remove final param chunk
        // Make new Command with found parameters, passed as List
        Command newCommand = Command.createCommand(chunk, paramList);
        // Return command
        return newCommand;
    }

    // Checks to see if String chunk is a number
    private boolean isNumeric(String input) {
        return false;
    }
}
