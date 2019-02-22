package parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {

    public static final String COMMAND_INFO_FILENAME = "CommandInfo.txt";
    public static final String COMMENT_CHAR = "#";
    public static final String COMMAND_NAME_DELIMITER = "=";
    public static final String COMMAND_INFO_DELIMITER = ":";

    private static CommandFactory instance;
    private Map<String, String> commandClassNames;
    private Map<String, Integer> commandParamCounts;

    private CommandFactory() throws ParserException {
        commandClassNames = new HashMap<>();
        commandParamCounts = new HashMap<>();
        initClassMaps();
    }

    public static CommandFactory getInstance() throws ParserException {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    /*
     * Create an instance of a command
     *
     * @param commandName The name of the command
     * @param args        The arguments to give to the command
     */
    //public static Command createCommand(String commandName, List<Command> args) {
//
  //      Class clazz = Class.forName(commandName)

    //}


    private void initClassMaps() throws ParserException {

        File file = new File(getClass().getClassLoader().getResource(COMMAND_INFO_FILENAME).getFile());

        try (Scanner scan = new Scanner(file)) {

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.strip().startsWith(COMMENT_CHAR))
                    continue;

                String[] commandNameSplit = line.strip().split(COMMAND_NAME_DELIMITER);
                String commandName = commandNameSplit[0];

                String[] commandInfoSplit = commandNameSplit[1].split(COMMAND_INFO_DELIMITER);
                String commandClassname = commandInfoSplit[0];
                int commandParamsCount = Integer.parseInt(commandInfoSplit[1]);

                commandClassNames.put(commandName, commandClassname);
                commandParamCounts.put(commandName, commandParamsCount);
            }

        } catch (IOException e) {
            throw new ParserException("Unable to read Command Info file");
        } catch (NumberFormatException e) {
            throw new ParserException("Invalid Command Info file format, did not receive integer");
        }
        int one = 2;
    }

    public int getParamCount(String command) {
        return commandParamCounts.get(command);
    }

    public static void main(String args[]) throws ParserException {
        CommandFactory.getInstance();
    }
}
