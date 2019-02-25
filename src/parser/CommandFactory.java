package parser;

import parser.commands.ConstantCommand;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class CommandFactory {

    private static final String COMMAND_INFO_FILENAME = "CommandInfo.txt";
    private static final String COMMENT_CHAR = "#";
    private static final String COMMAND_NAME_DELIMITER = "=";
    private static final String COMMAND_INFO_DELIMITER = ":";

    private static CommandFactory instance;
    private Map<String, String> commandClassNames;
    private Map<String, Integer> commandParamCounts;

    private CommandFactory() throws ParserException {
        commandClassNames = new HashMap<>();
        commandParamCounts = new HashMap<>();
        initClassMaps();
    }

    static CommandFactory getInstance() throws ParserException {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    /**
     * Create an instance of a command
     *
     * @param commandName The name of the command
     * @param args        The arguments to give to the command
     */
    Command createCommand(String commandName, List<Command> args) throws ParserException {

        Class clazz = null;
        try {

            clazz = Class.forName("parser.commands." + commandClassNames.get(commandName));
            Constructor constructor = clazz.getConstructor(List.class);
            return (Command)constructor.newInstance(args);

        } catch (ClassNotFoundException e) {
            throw new ParserException("Class " + commandClassNames.get(commandName) + " not found");
        } catch (NoSuchMethodException e) {
            throw new ParserException("Class " + clazz.getName() +
                    " does not have correct constructor");
        } catch (Exception e) {
            throw new ParserException("Error instantiating class for command " + commandName + "\n" + e);
        }

    }

    Command createConstantCommand(double value) {
        return new ConstantCommand(value);
    }

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
    }

    /**
     * Get the number of parameters required for a command
     * @param command The name of the command
     * @return The number of parameters required for the given command
     */
    int getParamCount(String command) {
        return commandParamCounts.get(command);
    }
}
