package parser;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

class InputTranslator {

    private static List<Map.Entry<String, Pattern>> mySymbols;
    private static final File LANGUAGE_FOLDER = new File(System.getProperty("user.dir") + "/src/resources/languages");
    private static final String RESOURCES_PREFIX = "resources/languages/";

    private static InputTranslator instance;

    private InputTranslator() throws ParserException {
        mySymbols = new ArrayList<>();
        populateEntries();
    }

    static InputTranslator getInstance() throws ParserException {
        if (instance == null)
            instance = new InputTranslator();
        return instance;
    }

    private void populateEntries() throws ParserException {
        File[] langList = LANGUAGE_FOLDER.listFiles(); // Import properties files, store entries
        if (langList != null) {
            for (File lang : langList) {
                if (lang.getName().equals("Syntax.properties")) {
                    continue;
                }
                addPatterns(lang.getName());
            }
            try {
                addPatterns("Syntax.properties"); // Saves basic syntax for last
            } catch (NullPointerException e) {
                throw new ParserException("No syntax properties file found");
            }
        }
        else {
            throw new ParserException("Languages directory is empty");
        }
    }

    private void addPatterns(String filename) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PREFIX + filename.replace(".properties", ""));
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE))); //TODO check to see if toLowerCase is necessary
        }
    }

    String getSymbol(String symbol) throws ParserException {
        for (Map.Entry<String, Pattern> entry : mySymbols) {
            if (entry.getValue().matcher(symbol).matches()) {
                if (!entry.getKey().equals("Constant") && !entry.getKey().equals("Variable")) {
                    return entry.getKey();
                }
                return symbol; // Returns original input if constant or name of user defined variable
            }
        }
        throw new ParserException("Invalid syntax");
    }
}
