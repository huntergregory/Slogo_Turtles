package parser_public;

import parser_private.CommandFactory;
import parser_private.commands.control_commands.VariableCommand;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import parser_private.Command;

public class CommandParser {

    private Queue<Command> myCommandQueue;
    private int myChunkIndex;
    private List<String> myCommandHistory;
    private static final String WHITESPACE_REGEX = "\\s+";
    private static CommandParser instance;

    private CommandParser() {
        myChunkIndex = 0;
        myCommandQueue = new LinkedList<>();
        myCommandHistory = new ArrayList<>();
    }

    public static CommandParser getInstance() {
        if (instance == null)
            instance = new CommandParser();
        return instance;
    }

    public void parseAndRun(String program) throws ParserException {
        myCommandHistory.add(program);
        try {
            parseProgram(program);
        }
        catch (ParserException e) { // Only store valid commands in history (ones that don't produce parsing exceptions)
            myCommandHistory.remove(myCommandHistory.size() - 1);
            throw e;
        }
        runProgram();
    }

    // Loops until overall input is empty
    private void parseProgram(String program) throws ParserException {
        if (program.isEmpty()) {
            throw new ParserException("Empty input string");
        }
        List<String> programChunks = getChunks(program);
        System.out.println(programChunks);
        myChunkIndex = 0;
        while (myChunkIndex < programChunks.size()) {
            Command nextCommand = makeCommand(programChunks); // Get next command
            myCommandQueue.add(nextCommand);
            runProgram(); //Execute each full command as it's parsed to allow for To definition and call in same program (as separate commands)
        }
    }

    private List<String> getChunks(String input) throws ParserException {
        List<String> chunks = new ArrayList<>();

        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase().strip();
            if (InputTranslator.getInstance().isComment(currentLine) || currentLine.isEmpty()) {
                continue;
            }

            String[] currentChunks = currentLine.split(WHITESPACE_REGEX);
            for (String s : currentChunks) {
                chunks.add(InputTranslator.getInstance().getSymbol(s));
            }
        }
        return chunks;
    }

    // Loops until individual command hierarchy is satisfied
    private Command makeCommand(List<String> input) throws ParserException {
        String currentChunk = input.get(myChunkIndex);                          // Advance chunk
        if (InputTranslator.getInstance().isConstant(currentChunk)) {           // Return constant if constant
            myChunkIndex++;
            return CommandFactory.getInstance().createConstantCommand(Double.parseDouble(currentChunk));
        } else if (InputTranslator.getInstance().isVariable(currentChunk)) {    // Return new user defined variable reference if need be
            myChunkIndex++;
            return new VariableCommand(currentChunk.substring(1));
        } else if (!CommandFactory.getInstance().isNormalCommand(currentChunk)) {
            if (myChunkIndex > 0 && input.get(myChunkIndex - 1).equals("MakeUserInstruction")) {
                myChunkIndex++;                                                 // Check if otherwise invalid command is preceded by "to", return new var ref if so
                return new VariableCommand(currentChunk);
            }
            else if (!GlobalCommands.getInstance().isDefined(currentChunk)) {
                throw new ParserException("Invalid command");                   // Else, invalid command
            }
        }
        List<Command> paramList = getParameters(currentChunk, input);
        if (CommandFactory.getInstance().isNormalCommand(currentChunk)) {       // Return complete command
            return CommandFactory.getInstance().createCommand(currentChunk, paramList);
        }
        return GlobalCommands.getInstance().getCommand(currentChunk, paramList);
    }

    private List<Command> getParameters(String command, List<String> input) throws ParserException {
        int numParams = 0;
        List<Command> paramList = new ArrayList<>();
        if (CommandFactory.getInstance().isNormalCommand(command)) {         // If we've made it this far, command must already exist, so find it:
            numParams = CommandFactory.getInstance().getParamCount(command); // Normal command takes priority over user defined command of same name
        }
        else if (GlobalCommands.getInstance().isDefined(command)){
            numParams = GlobalCommands.getInstance().getParamCount(command); // User defined command
        }
        myChunkIndex++;
        if (numParams == -1) {                                               // -1 means a list should be created, until an end bracket
            populateUntilListEnd(paramList, input);
        }
        else {
            populateNParameters(paramList, numParams, input);
        }
        return paramList;
    }

    private void populateNParameters(List<Command> paramList, int num, List<String> chunkList) throws ParserException {
        for (int i = 0; i < num; i++) {
            if (myChunkIndex == chunkList.size()) {
                throw new ParserException("Invalid parameter count");
            }
            paramList.add(makeCommand(chunkList));
        }
    }

    private void populateUntilListEnd(List<Command> paramList, List<String> chunkList) throws ParserException {
        while (!chunkList.get(myChunkIndex).equals("ListEnd")) {
            if (myChunkIndex == chunkList.size() - 1) {
                throw new ParserException("Unterminated list");
            }
            paramList.add(makeCommand(chunkList));
        }
        myChunkIndex++;
    }

    private void runProgram() {
        while (myCommandQueue.size() > 0) {
            myCommandQueue.remove().execute();
        }
    }

    public List<String> getCommandHistory() {
        return myCommandHistory;
    }
}
