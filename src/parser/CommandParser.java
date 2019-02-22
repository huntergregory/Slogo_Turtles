package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public final class CommandParser {

    private Queue<Command> myCommandQueue;
    private int myChunkIndex;

    private static CommandParser instance;

    public static final String COMMENT_CHAR = "#";
    public static final String NUMBER_REGEX = "^-?[0-9]+\\.?[0-9]*";

    private CommandParser() {
        myChunkIndex = 0;
        myCommandQueue = new PriorityQueue<>();
    }

    public static CommandParser getInstance() {
        if (instance == null)
            instance = new CommandParser();
        return instance;
    }

    // Loops until overall input is empty
    public void parse(String program) throws ParserException {
        parseProgram(program);
        runProgram();
    }

    private void parseProgram(String program) throws ParserException {
        if (program.isEmpty()) {
            throw new ParserException("Empty input string!");
        }
        String translatedInput = translateInput(program);
        String[] programChunks = translatedInput.split("\\s+");
        myChunkIndex = 0;
        while (myChunkIndex < programChunks.length) {
            Command nextCommand = makeCommand(programChunks); // Get next command
            myCommandQueue.add(nextCommand);
        }
    }

    private void runProgram() {
        while (myCommandQueue.size() > 0) {
            myCommandQueue.remove().execute();
        }
    }

    private String translateInput(String input) throws ParserException {
        StringBuilder newInput = new StringBuilder();
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase();
            if (currentLine.strip().startsWith(COMMENT_CHAR)) {
                continue;
            }
            String[] currentChunks = currentLine.split("\\s+");
            for (String s : currentChunks) {
                newInput.append(InTranslator.getSymbol(s));
                newInput.append(" ");
            }
        }
        return newInput.toString();
    }

    // Loops until individual command hierarchy is satisfied
    private Command makeCommand(String[] input) throws ParserException {
        String currentChunk = input[myChunkIndex];
        if (currentChunk.matches(NUMBER_REGEX)) {
            //return CommandFactory.getInstance().createCommand("VAL", Double.parseDouble(currentChunk));
            // TODO proper number handling
        }
        int numParams = CommandFactory.getInstance().getParamCount(currentChunk);
        List<Command> paramList = new ArrayList<>(numParams);
        for (int i = 0; i < numParams; i++) {
            myChunkIndex++;
            paramList.set(i, makeCommand(input));
        }
        myChunkIndex++;
        return CommandFactory.getInstance().createCommand(currentChunk, paramList);
    }

    public Queue<Command> getCommandQueue() {
        return myCommandQueue;
    }

    public static void main(String args[]) throws ParserException {
        CommandParser.getInstance().parse("FD 50");
    }
}
