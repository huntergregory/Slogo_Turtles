package state;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Harry Ross
 */
public class InputTranslator {

    private List<Map.Entry<String, Pattern>> myCurrentLanguage;
    private ObservableList<String> myLanguageList = FXCollections.observableList(new ArrayList<>());
    private static final String LANGUAGES_DIRECTORY = "languages/";
    private static final File LANG_FOLDER_FILE = new File(System.getProperty("user.dir") + "/src/state_manager/resources_state_manager/languages");
    private static final String MULTI_INPUT_FILENAME = "MultiInputEnabled";
    private static final String WHITESPACE_REGEX = "\\s+";

    public InputTranslator() throws ParserException {
        changeLanguage("English");
        populateLanguageList();
    }

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

    // returns true if command is defined in keyset (Commands are still original text)
    public boolean isNormalCommand(String command) {
        for (Map.Entry<String, Pattern> entry : myCurrentLanguage) {
            if (entry.getKey().equals(command)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMultiInputGrouping(String command) {
        command = command.substring(command.lastIndexOf(".") + 1);
        ResourceBundle validOptions = ResourceBundle.getBundle(MULTI_INPUT_FILENAME);
        return Collections.list(validOptions.getKeys()).contains(command);
    }

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

    public ObservableList<String> getLanguages() {
        return myLanguageList;
    }

    private boolean isComment(String text) {
        return matches("Comment", text);
    }

    public boolean isVariable(String text) {
        return matches("Variable", text);
    }

    public boolean isConstant(String text) {
        return matches("Constant", text);
    }
}
