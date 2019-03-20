package state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that maintains language settings and translates from selected language into standardized command names
 * Default language is assumed to be English until changed by user
 * Ex. InputTranslator intrns = new InputTranslator();
 *     intrns.changeLanguage("French");
 * @author Harry Ross
 */
public class InputTranslator {

    private List<Map.Entry<String, Pattern>> myCurrentLanguage;
    private ObservableList<String> myLanguageList = FXCollections.observableList(new ArrayList<>());
    private static final String LANGUAGES_DIRECTORY = "languages/";
    private static final File LANG_FOLDER_FILE = new File(System.getProperty("user.dir") + "/src/state_manager/resources_state_manager/languages");
    private static final String MULTI_INPUT_FILENAME = "MultiInputEnabled";
    private static final String WHITESPACE_REGEX = "\\s+";

    /**
     * Creates new InputTranslator instance, sets default language to English
     * @throws ParserException if error occurs with resource files.
     */
    public InputTranslator() throws ParserException {
        changeLanguage("English");
        populateLanguageList();
    }

    /**
     * Changes language of translator to given language
     * @param language New language to use for translations
     * @throws ParserException if an error occurs when locating or using a resource file relating to new language
     */
    public void changeLanguage(String language) throws ParserException {
        myCurrentLanguage = new ArrayList<>();
        try {
            addPatterns(language + ".properties");
        } catch (NullPointerException e) {
            throw new ParserException("Specified language resource not found");
        }
        try {
            addPatterns("Syntax.properties"); // Saves basic syntax for last
        } catch (NullPointerException e) {
            throw new ParserException("No syntax properties file found");
        }
    }

    private void addPatterns(String filename) {
        ResourceBundle resources = ResourceBundle.getBundle(LANGUAGES_DIRECTORY + filename.replace(".properties", ""));
        for (String key : Collections.list(resources.getKeys())) {
            String translation = resources.getString(key);
            myCurrentLanguage.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(translation, Pattern.CASE_INSENSITIVE)));
        }
    }

    private String getSymbol(String symbol) throws ParserException {
        for (Map.Entry<String, Pattern> entry : myCurrentLanguage) {
            if (entry.getValue().matcher(symbol).matches()) {
                if (!entry.getKey().equals("Constant") && !entry.getKey().equals("Variable") && !entry.getKey().equals("Command")) {
                    return entry.getKey();
                }
                return symbol; // Returns original input if constant or name of user defined variable or defined command
            }
        }
        throw new ParserException("Invalid command or syntax");
    }

    private boolean matches(String property, String text) {
        for (Map.Entry<String, Pattern> entry : myCurrentLanguage)
            if (entry.getKey().equals(property))
                return entry.getValue().matcher(text).matches();
        return false;
    }

    /** Returns true if command is defined in current language resource file
     * @param command String name of command to check
     * @return True if command exists in resource file
     */
    public boolean isNormalCommand(String command) {
        for (Map.Entry<String, Pattern> entry : myCurrentLanguage) {
            if (entry.getKey().equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given command is within a special subset of commands that behave differently when grouped
     * @param command String name of command to check
     * @return True if command is found within this subset
     */
    public boolean hasMultiInputGrouping(String command) {
        command = command.substring(command.lastIndexOf(".") + 1);
        ResourceBundle validOptions = ResourceBundle.getBundle(MULTI_INPUT_FILENAME);
        return Collections.list(validOptions.getKeys()).contains(command);
    }

    /**
     * Splits input into List of Strings for each term separated by whitespace
     * @param input String program input
     * @return List of split-up elements of original input
     * @throws ParserException if error occurs during splitting process
     */
    public List<String> getChunks(String input) throws ParserException {
        List<String> chunks = new ArrayList<>();
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine().toLowerCase().strip();
            if (isComment(currentLine) || currentLine.isEmpty()) {
                continue;
            }
            String[] currentChunks = currentLine.split(WHITESPACE_REGEX);
            for (String s : currentChunks) {
                chunks.add(getSymbol(s));
            }
        }
        return chunks;
    }

    private void populateLanguageList() {
        File[] fileList = LANG_FOLDER_FILE.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                String name = file.getName().substring(0, file.getName().indexOf('.'));
                if (!name.equals("Syntax")) {
                    myLanguageList.add(name);
                }
            }
        }
    }

    /**
     * Returns ObservableList of languages available to choose from
     * @return ObservableList of languages
     */
    public ObservableList<String> getLanguages() {
        return myLanguageList;
    }

    private boolean isComment(String text) {
        return matches("Comment", text);
    }

    /**
     * Checks to see if a given input is formatted as a variable as defined in the Syntax resource file
     * @param text Input to check
     * @return True if formatted as variable
     */
    public boolean isVariable(String text) {
        return matches("Variable", text);
    }

    /**
     * Checks to see if a given input is formatted as a constant as defined in the Syntax resource file
     * @param text Input to check
     * @return True if formatted as constant
     */
    public boolean isConstant(String text) {
        return matches("Constant", text);
    }
}
