package parser_public;

import parser_private.CommandFactory;
import parser_private.commands.control_commands.VariableCommand;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import state_public.CommandInter;
import state_public.ParserException;
import state_public.StateManager;

public class CommandParser {

    private Queue<CommandInter> myCommandQueue;
    private int myChunkIndex;
    private static final String WHITESPACE_REGEX = "\\s+";

    private StateManager myStateManager;
    private CommandFactory myCommandFactory;

    public CommandParser(StateManager stateManager) throws ParserException {
        myStateManager = stateManager;
        myCommandFactory = new CommandFactory(myStateManager);
        myChunkIndex = 0;
        myCommandQueue = new LinkedList<>();
    }

    public void parseAndRun(String program) throws ParserException {
        parseProgram(program);
        //myStateManager.getCommandHistory().addCommand(program); //TODO fix
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
            CommandInter nextCommand = makeCommand(programChunks); // Get next command
            myCommandQueue.add(nextCommand);
            nextCommand.injectStateManager(myStateManager);
            runProgram(); //Execute each full command as it's parsed to allow for To definition and call in same program (as separate commands)
        }
    }

    private List<String> getChunks(String input) throws ParserException {
        List<String> chunks = new ArrayList<>();

        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase().strip();
            if (myStateManager.getInputTranslator().isComment(currentLine) || currentLine.isEmpty()) {
                continue;
            }

            String[] currentChunks = currentLine.split(WHITESPACE_REGEX);
            for (String s : currentChunks) {
                chunks.add(myStateManager.getInputTranslator().getSymbol(s));
            }
        }
        return chunks;
    }

    // Loops until individual command hierarchy is satisfied
    private CommandInter makeCommand(List<String> input) throws ParserException {
        String currentChunk = input.get(myChunkIndex);                          // Advance chunk
        if (myStateManager.getInputTranslator().isConstant(currentChunk)) {           // Return constant if constant
            myChunkIndex++;
            return myCommandFactory.createConstantCommand(Double.parseDouble(currentChunk));
        } else if (myStateManager.getInputTranslator().isVariable(currentChunk)) {    // Return new user defined variable reference if need be
            myChunkIndex++;
            return new VariableCommand(currentChunk.substring(1));
        } else if (!myCommandFactory.isNormalCommand(currentChunk)) {
            if (myChunkIndex > 0 && input.get(myChunkIndex - 1).equals("MakeUserInstruction")) {
                myChunkIndex++;                                                 // Check if otherwise invalid command is preceded by "to", return new var ref if so
                return new VariableCommand(currentChunk);
            }
            else if (!myStateManager.getCommands().isDefined(currentChunk)) {
                throw new ParserException("Invalid command");                   // Else, invalid command
            }
        }
        List<CommandInter> paramList = getParameters(currentChunk, input);
        if (myCommandFactory.isNormalCommand(currentChunk)) {       // Return complete command
            return myCommandFactory.createCommand(currentChunk, paramList);
        }
        return myStateManager.getCommands().getCommand(currentChunk, paramList);
    }

    private List<CommandInter> getParameters(String command, List<String> input) throws ParserException {
        int numParams = 0;
        List<CommandInter> paramList = new ArrayList<>();
        if (myCommandFactory.isNormalCommand(command)) {         // If we've made it this far, command must already exist, so find it:
            numParams = myCommandFactory.getParamCount(command); // Normal command takes priority over user defined command of same name
        }
        else if (myStateManager.getCommands().isDefined(command)){
            numParams = myStateManager.getCommands().getParamCount(command); // User defined command
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

    private void populateNParameters(List<CommandInter> paramList, int num, List<String> chunkList) throws ParserException {
        for (int i = 0; i < num; i++) {
            if (myChunkIndex == chunkList.size()) {
                throw new ParserException("Invalid parameter count");
            }
            paramList.add(makeCommand(chunkList));
        }
    }

    private void populateUntilListEnd(List<CommandInter> paramList, List<String> chunkList) throws ParserException {
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

    public static void main(String[] args) throws ParserException {
        // ------ TEST CASES ------
        CommandParser test = new CommandParser(new StateManager());
        //test.parseAndRun("dotimes [ :john 5 ] [ fd :john ]"); //WORKS
        //test.parseAndRun("set :bule 1 if :bule [ dotimes [ :john 5 ] [ fd :john ] ]"); //WORKS
        //test.parseAndRun("dotimes [ :a 2 ] [ dotimes [ :b 4 ] [ fd :a fd :b ] ] fd sum :a :b"); //WORKS
        //test.parseAndRun("set :a 4 set :b 7 fd :a fd :b fd sum :a :b fd :c"); //WORKS
        //test.parseAndRun("repeat 5 [ fd :repcount repeat 2 [ fd :repcount ] ] fd :repcount"); //WORKS
        //test.parseAndRun("fd not or 1 0"); //WORKS
        //test.parseAndRun("1 and 4"); //THROWS PARSEREXCEPTION AS IT SHOULD
        //test.parseAndRun("repeat 3 [ make :a sum 8 :repcount fd :a ] fd :a fd :repcount"); //WORKS
        //test.parseAndRun("ifelse and 1 1 [ fd 4 fd 9 ] [ fd 6 fd 7 ]"); //WORKS
        test.parseAndRun("to funca [ :a :b ] [ fd sum :a :b ] to funcb [ :a :b ] [ fd difference :a :b ] to funcc [ :a :b ] [ funca :a :b funcb :a :b ] funcc 9 2");
    }
}
