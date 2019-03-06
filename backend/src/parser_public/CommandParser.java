package parser_public;

import parser_private.CommandFactory;
import parser_private.commands.control_commands.ListCommand;
import parser_private.commands.control_commands.VariableCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import state_public.CommandInter;
import state_public.ParserException;
import state_public.StateManager;

public class CommandParser {

    private int myChunkIndex;
    private static final String WHITESPACE_REGEX = "\\s+";

    private StateManager myStateManager;
    private CommandFactory myCommandFactory;

    public CommandParser(StateManager stateManager) throws ParserException {
        myStateManager = stateManager;
        myCommandFactory = new CommandFactory(myStateManager);
        myChunkIndex = 0;
    }

    // Parses and executes individual commands until overall input is empty
    public void parseAndRun(String program) throws ParserException {
        if (program.isEmpty()) {
            throw new ParserException("Empty input string");
        }
        List<String> programChunks = getChunks(program);
        System.out.println(programChunks);
        myChunkIndex = 0;
        while (myChunkIndex < programChunks.size()) {
            CommandInter nextCommand = makeCommand(programChunks); // Get next command
            nextCommand.injectStateManager(myStateManager);
            nextCommand.execute(); //Execute each full command as it's parsed to allow for To definition and call in same program (as separate commands)
        }
        //myStateManager.getCommandHistory().addCommand(program); //TODO fix
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
        if (myStateManager.getInputTranslator().isGroupStart(currentChunk)) {
            myChunkIndex++;
            return groupParse(input);
        }
        if (myStateManager.getInputTranslator().isGroupEnd(currentChunk)) {
            throw new ParserException("Unexpected group end");
        }
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
        return createCommand(currentChunk, paramList);
    }

    private CommandInter createCommand(String command, List<CommandInter> params) throws ParserException {
        if (myCommandFactory.isNormalCommand(command)) {       // Return complete command
            return myCommandFactory.createCommand(command, params);
        }
        return myStateManager.getCommands().getCommand(command, params);
    }

    private CommandInter groupParse(List<String> input) throws ParserException {
        String baseCommand = input.get(myChunkIndex);
        validateCommand(baseCommand);
        List<CommandInter> params;
        List<CommandInter> group =  new ArrayList<>();
        if (myStateManager.getInputTranslator().hasMultiInputGrouping(baseCommand)) { // Equals 3 5 6 3 2 GroupEnd; Sum 3 5 6 3 2 GroupEnd
                myChunkIndex++; // move index beyond base command
                params = new ArrayList<>();
                populateUntilListEnd(params, input);
                group.add(createCommand(baseCommand, params));
        }
        else { // (SEQUENTIAL GROUPING) ForwardCommand 50 70 20 2 GroupEnd
            params = getParameters(baseCommand, input); //needs to have command name be current chunk, increments chunk index by numparams
            group.add(createCommand(baseCommand, params)); //adds first command to list // 70 20 2 GroupEnd
            while (!myStateManager.getInputTranslator().isGroupEnd(input.get(myChunkIndex))) {
                input.add(myChunkIndex, baseCommand); // ForwardCommand 70 20 2 GroupEnd
                params = getParameters(baseCommand, input); //increments chunk index by numParams
                group.add(createCommand(baseCommand, params));
                if (myChunkIndex == input.size()) {
                    throw new ParserException("Unterminated group");
                }
            }
            myChunkIndex++;
        }
        return new ListCommand(group);
    }

    private void validateCommand(String command) throws ParserException {
        if (!myCommandFactory.isNormalCommand(command) && !myStateManager.getCommands().isDefined(command)) {
            throw new ParserException("Invalid command");
        }
    }

    private List<CommandInter> getParameters(String command, List<String> input) throws ParserException {
        List<CommandInter> paramList = new ArrayList<>();
        int numParams = getParamCount(command);
        myChunkIndex++;
        if (numParams == -1) {                                               // -1 means a list should be created, until an end bracket
            populateUntilListEnd(paramList, input);
        }
        else {
            populateNParameters(paramList, numParams, input);
        }
        return paramList;
    }

    private int getParamCount(String command) {
        if (myCommandFactory.isNormalCommand(command)) {    // If we've made it this far, command must already exist, so find it:
            return myCommandFactory.getParamCount(command); // Normal command takes priority over user defined command of same name
        }
        return myStateManager.getCommands().getParamCount(command); // User defined command
    }

    private void populateNParameters(List<CommandInter> paramList, int num, List<String> chunkList) throws ParserException {
        for (int i = 0; i < num; i++) {
            if (myChunkIndex == chunkList.size()) {
                throw new ParserException("Invalid parameter count");
            }
            paramList.add(makeCommand(chunkList)); //chunk index gets increased in makeCommand, not here
        }
    }

    private void populateUntilListEnd(List<CommandInter> paramList, List<String> input) throws ParserException {
        while (!(input.get(myChunkIndex).equals("ListEnd") || input.get(myChunkIndex).equals("GroupEnd"))) {
            paramList.add(makeCommand(input));
            if (myChunkIndex == input.size()) {
                throw new ParserException("Unterminated list or group");
            }
        }
        myChunkIndex++;
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
        //test.parseAndRun("to funca [ :a :b ] [ fd sum :a :b ] to funcb [ :a :b ] [ fd difference :a :b ] to funcc [ :a :b ] [ funca :a :b funcb :a :b ] funcc 9 2"); //WORKS
        //test.parseAndRun("fd 1 ( goto 5 6 7 8 fd 50 4 )"); //WORKS
        //test.parseAndRun("[ fd 4 fd 4 fd 3 ]"); //WORKS
        //test.parseAndRun("fd ( equal? 3 3 ( fd 3 ) ) fd 50"); //WORKS
        //test.parseAndRun("fd 50 fd ( sin 90 5 90 ) fd 42"); //WORKS
        //test.parseAndRun("fd ( or 0 1 0 )"); //WORKS
        //test.parseAndRun("fd ( product 2 31 3 )"); //WORKS
        //test.parseAndRun("dev ( egal? 3 3 ( dev 3 ) ) dev 50"); //WORKS
    }
}
