package parser;

import parser_private.CommandFactory;
import state.ICommand;
import state.ParserException;
import state.StateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that controls translating input into Command objects and executing them.
 * Constructor takes in a StateManager object to allow for created Commands to share information.
 * Depends on CommandFactory, ICommand, ParserException, and StateManager classes.
 * Ex. CommandParser cdpsr = new CommandParser(stateManager);
 *     cdpsr.parseAndRun("fd 50 lt 90 fd 50");
 * @author Harry Ross
 */
public class CommandParser {

    private int myChunkIndex;
    private List<String> myInputChunks;
    private StateManager myStateManager;
    private CommandFactory myCommandFactory;

    /**
     * Creates a new instance of CommandParser with given StateManager
     * @param stateManager Inherited StateManager for a given workspace
     * @throws ParserException when errors occur in initializing CommandFactory
     */
    public CommandParser(StateManager stateManager) throws ParserException {
        myStateManager = stateManager;
        myCommandFactory = new CommandFactory(myStateManager);
        myChunkIndex = 0;
    }

    /**
     * Parses and executes individual commands until overall input is empty
     * @param program String input to be parsed, translated, and executed
     * @throws ParserException when syntax errors, spelling errors, etc. occur during parsing
     */
    public void parseAndRun(String program) throws ParserException {
        if (program.isEmpty()) {
            throw new ParserException("Empty input string");
        }
        myInputChunks = myStateManager.getInputTranslator().getChunks(program);
        myChunkIndex = 0;
        while (myChunkIndex < myInputChunks.size()) {
            ICommand nextCommand = makeNextCommand();
            nextCommand.injectStateManager(myStateManager);
            try {
                nextCommand.execute();
            } catch (IndexOutOfBoundsException | ClassCastException e) {
                throw new ParserException("Invalid listed parameter count or parameter type for command");
            }
        }
        myStateManager.getCommandHistory().addCommand(program);
    }

    // Loops until individual command hierarchy is satisfied
    private ICommand makeNextCommand() throws ParserException {
        boolean overwriteEnable = false;
        if (myChunkIndex > 0 && myInputChunks.get(myChunkIndex - 1).equals("MakeUserInstruction")) {
            overwriteEnable = true;
        }
        String currentChunk = myInputChunks.get(myChunkIndex); // Advance chunk
        List<ICommand> paramList = getParameters(currentChunk, overwriteEnable);
        return myCommandFactory.createCommand(currentChunk, paramList, overwriteEnable);
    }

    private List<ICommand> getParameters(String command, boolean overwriteEnable) throws ParserException {
        int numParams = myCommandFactory.getParamCount(command, overwriteEnable);
        myChunkIndex++;
        List<ICommand> paramList = new ArrayList<>();
        int i = 0;
        while (i != numParams) {
            if (numParams == -1 && myInputChunks.get(myChunkIndex).matches(".*End")) {
                myChunkIndex++;
                break;
            }
            if (myChunkIndex == myInputChunks.size()) {
                throw new ParserException("Invalid parameter count or unterminated list or group");
            }
            paramList.add(makeNextCommand());
            i++;
        }
        return paramList;
    }
}
