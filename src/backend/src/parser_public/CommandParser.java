package parser_public;

import parser_private.CommandFactory;
import java.util.ArrayList;
import java.util.List;
import state_public.ICommand;
import state_public.ParserException;
import state_public.StateManager;

public class CommandParser {

    private int myChunkIndex;
    private List<String> myInputChunks;
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
        myInputChunks = myStateManager.getInputTranslator().getChunks(program);
        myChunkIndex = 0;
        System.out.println(myInputChunks);
        while (myChunkIndex < myInputChunks.size()) {
            ICommand nextCommand = makeNextCommand(); // Get next command
            nextCommand.injectStateManager(myStateManager);
            nextCommand.execute();
        }
        //myStateManager.getCommandHistory().addCommand(program); //TODO
    }

    // Loops until individual command hierarchy is satisfied
    private ICommand makeNextCommand() throws ParserException {
        String currentChunk = myInputChunks.get(myChunkIndex); // Advance chunk
        List<ICommand> paramList = getParameters(currentChunk);
        return myCommandFactory.createCommand(currentChunk, paramList);
    }

    private List<ICommand> getParameters(String command) throws ParserException {
        int numParams = myCommandFactory.getParamCount(command);
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
        //test.parseAndRun("fd 50 fd ( sin 90 5 90 ) fd 42"); //WORKS
        //test.parseAndRun("fd ( or 0 1 0 )"); //WORKS
        //test.parseAndRun("fd ( product 2 31 3 )"); //WORKS
        //test.parseAndRun("dev ( egal? 3 3 ( dev 3 ) ) dev 50"); //WORKS
        //test.parseAndRun("fd ( equal? 3 3 ( fd 3 ) ) fd 50"); //WORKS
        //test.parseAndRun("( fd 50 )"); //WORKS
        //test.parseAndRun("dfsdf weotiwe wfw to wfw [ :d :dfsdf ] [ if [ equal? :d 8 ] [ ( fd :d :dfsdf ) ] ] wfw sum 6 2 9"); //WORKS
        //test.parseAndRun("to xcor [ :a ] [ fd sum 3 5 ] xcor 5"); //Throws error when trying to overwrite, works
        //test.parseAndRun("dev 20"); //WORKS
        //test.parseAndRun("( xcor 4 5 fd 6 7 )"); //WORKS
        //test.parseAndRun(" fd ( or 0 0 0 ) fd ( or 0 1 0 ) fd ( or 1 1 1 ) fd ( and 0 0 0 ) fd ( and 0 1 0 ) fd ( and 1 1 1 )"); //WORKS
    }
}
