package parser;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

final class InputTranslator {

    private static List<Map.Entry<String, Pattern>> mySymbols;
    private static final File LANGUAGE_FOLDER = new File(System.getProperty("user.dir") + "/src/resources/languages");
    private static final String RESOURCES_PREFIX = "resources/languages/";

    private static InputTranslator instance;

    private InputTranslator() {
        mySymbols = new ArrayList<>();
        // import properties files, store
        File[] langList = LANGUAGE_FOLDER.listFiles();
        for (File lang : langList) {
            if (lang.getName().equals("Syntax.properties")) {
                continue;
            }
            addPatterns(lang.getName());
        }
        addPatterns("Syntax.properties"); // Saves basic syntax for last
    }

    static InputTranslator getInstance() {
        if (instance == null)
            instance = new InputTranslator();
        return instance;
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
        throw new ParserException("Invalid syntax.");
    }
}
