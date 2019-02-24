package parser;

import parser.commands.control_commands.VariableCommand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

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
        myChunkIndex = 0;
        while (myChunkIndex < programChunks.size()) {
            Command nextCommand = makeCommand(programChunks); // Get next command
            myCommandQueue.add(nextCommand);
        }
    }

    private List<String> getChunks(String input) throws ParserException {
        List<String> chunks = new ArrayList<>();

        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase().strip();
            if (InputTranslator.getInstance().isComment(currentLine)) {
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
        String currentChunk = input.get(myChunkIndex);

        if (InputTranslator.getInstance().isConstant(currentChunk)) {
            myChunkIndex++;
            return CommandFactory.getInstance().createConstantCommand(Double.parseDouble(currentChunk));
        } else if (InputTranslator.getInstance().isVariable(currentChunk)) {
            myChunkIndex++;
            return new VariableCommand(currentChunk.substring(1));
        }
        int numParams = CommandFactory.getInstance().getParamCount(currentChunk);
        List<Command> paramList = new ArrayList<>();
        myChunkIndex++;

        if (numParams == -1) // -1 means a list should be created, until an end bracket
            populateUntilListEnd(paramList, input);
        else {
            populateNParameters(paramList, numParams, input);
        }
        return CommandFactory.getInstance().createCommand(currentChunk, paramList);
    }

    private void populateNParameters(List<Command> paramList, int num, List<String> chunkList) throws ParserException {
        for (int i = 0; i < num; i++) {
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

    public static void main(String[] args) throws ParserException {
        //CommandParser.getInstance().parseAndRun("dotimes [ :john 5 ] [ fd :john ]");
        CommandParser.getInstance().parseAndRun("set :bule 7 if :bule [ dotimes [ :john 5 ] [ fd :john ] ]");
    }
}
