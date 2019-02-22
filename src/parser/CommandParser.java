package parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public final class CommandParser {

    private Queue<Command> myCommandQueue;
    private int myChunkIndex;

    private static CommandParser instance;

    private static final String COMMENT_CHAR = "#";
    private static final String NUMBER_REGEX = "^-?[0-9]+\\.?[0-9]*";
    private static final String WHITESPACE_REGEX = "\\s+";

    private CommandParser() {
        myChunkIndex = 0;
        myCommandQueue = new LinkedList<>();
    }

    public static CommandParser getInstance() {
        if (instance == null)
            instance = new CommandParser();
        return instance;
    }


    public void parseAndRun(String program) throws ParserException {
        parseProgram(program);
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

    private void runProgram() {
        while (myCommandQueue.size() > 0) {
            myCommandQueue.remove().execute();
        }
    }

    private List<String> getChunks(String input) throws ParserException {
        List<String> chunks = new ArrayList<>();

        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase().strip();
            if (currentLine.startsWith(COMMENT_CHAR)) {
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
        if (currentChunk.matches(NUMBER_REGEX)) {
            return CommandFactory.getInstance().createConstantCommand(Double.parseDouble(currentChunk));
        }
        int numParams = CommandFactory.getInstance().getParamCount(currentChunk);
        List<Command> paramList = new ArrayList<>();
        for (int i = 0; i < numParams; i++) {
            myChunkIndex++;
            paramList.add(makeCommand(input));
        }
        myChunkIndex++;
        return CommandFactory.getInstance().createCommand(currentChunk, paramList);
    }

    public Queue<Command> getCommandQueue() {
        return myCommandQueue;
    }
}
