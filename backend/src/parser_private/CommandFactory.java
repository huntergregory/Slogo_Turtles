package parser_private;

import external.ExecutionContext;
import parser_private.commands.math_commands.ConstantCommand;
import parser_public.ParserException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {

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

    public static CommandFactory getInstance() throws ParserException {
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
    public Command createCommand(String commandName, List<Command> args, ExecutionContext executionContext) throws ParserException {

        Class clazz = null;
        try {

            clazz = Class.forName("parser_private.commands." + commandClassNames.get(commandName));
            Constructor[] constructors = clazz.getConstructors();
            if (constructors.length > 1)
                throw new ParserException("Class " + clazz.getName() + " should only have one constructor.");
            Constructor constructor = constructors[0];
            if (constructor.getParameterCount() == 1) {
                return (Command) constructor.newInstance(args);
            }
            else {
                if (executionContext.hasCommand(commandName))
                    return (Command) constructor.newInstance(args, executionContext.getExternalAPICall(commandName));
                else
                    throw new ParserException("External API Call for " + clazz.getName() + " does not exist");
            }

        } catch (ClassNotFoundException e) {
            throw new ParserException("Class " + commandClassNames.get(commandName) + " not found");
        } catch (Exception e) {
            throw new ParserException("Error instantiating class for command " + commandName + "\n" + e);
        }

    }

    public Command createConstantCommand(double value) {
        return new ConstantCommand(value);
    }

    private void initClassMaps() throws ParserException {

        File file = new File(getClass().getClassLoader().getResource(COMMAND_INFO_FILENAME).getFile());
        String path = URLDecoder.decode(file.getAbsolutePath(), StandardCharsets.UTF_8);
        file = new File(path);

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
    public int getParamCount(String command) {
        return commandParamCounts.get(command);
    }
}
