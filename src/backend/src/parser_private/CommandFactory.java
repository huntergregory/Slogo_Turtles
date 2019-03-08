package parser_private;

import parser_private.commands.control_commands.VariableCommand;
import parser_private.commands.math_commands.ConstantCommand;
import state_public.ICommand;
import state_public.ParserException;
import state_public.StateManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    private static final String INJECTION_METHODNAME = "injectStateManager";

    private Map<String, String> commandClassNames;
    private Map<String, Integer> commandParamCounts;

    private StateManager myStateManager;

    public CommandFactory(StateManager stateManager) throws ParserException {
        commandClassNames = new HashMap<>();
        commandParamCounts = new HashMap<>();
        myStateManager = stateManager;
        initClassMaps();
    }

    /**
     * Create an instance of a command
     *
     * @param commandName The name of the command
     * @param args        The arguments to give to the command
     */
    public ICommand createCommand(String commandName, List<ICommand> args) throws ParserException {
        if(prelimChecks(commandName) != null) return prelimChecks(commandName);
        if (myStateManager.getInputTranslator().isNormalCommand(commandName)) {
            Class clazz = null;
            try {

                clazz = Class.forName("parser_private.commands." + commandClassNames.get(commandName));
                Constructor constructor = clazz.getConstructor(List.class);
                Command command = (Command) constructor.newInstance(args);

                Method injection = Command.class.getMethod(INJECTION_METHODNAME, StateManager.class);
                injection.invoke(command, myStateManager);

                return command;

            } catch (ClassNotFoundException e) {
                throw new ParserException("Class " + commandClassNames.get(commandName) + " not found");
            } catch (NoSuchMethodException e) {
                throw new ParserException("Class " + clazz.getName() +
                        " does not have correct constructor");
            } catch (Exception e) {
                throw new ParserException("Error instantiating class for command " + commandName + "\n" + e);
            }
        }
        return myStateManager.getCommands().getCommand(commandName, args);
    }

    private Command createConstantCommand(double value) {
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
        if (myStateManager.getCommands().isDefined(command)) {
            return myStateManager.getCommands().getParamCount(command);
        }
        if (myStateManager.getInputTranslator().isNormalCommand(command)) {
            return commandParamCounts.get(command);
        }
        return 0;
    }

    private ICommand prelimChecks(String commandName) {
        if (myStateManager.getInputTranslator().isConstant(commandName)) {
            return createConstantCommand(Double.parseDouble(commandName));
        }
        if (!myStateManager.getInputTranslator().isNormalCommand(commandName) &&
                (myStateManager.getInputTranslator().isVariable(commandName) ||
                        !myStateManager.getCommands().isDefined(commandName))) {
            return new VariableCommand(commandName.replaceAll(":", ""));
        }
        return null;
    }
}