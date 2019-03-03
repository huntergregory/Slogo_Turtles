package state_public;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class InputTranslator {

    private static List<Map.Entry<String, Pattern>> myCurrentLanguage;
    private static final String RESOURCES_DIRECTORY = "languages/";

    public InputTranslator() throws ParserException {
        changeLanguage("English");
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
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_DIRECTORY + filename.replace(".properties", ""));
        for (String key : Collections.list(resources.getKeys())) {
            String translation = resources.getString(key);
            myCurrentLanguage.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(translation, Pattern.CASE_INSENSITIVE)));
        }
    }

    public String getSymbol(String symbol) throws ParserException {
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
        for (Map.Entry<String, Pattern> entry: myCurrentLanguage)
            if (entry.getKey().equals(property))
                return entry.getValue().matcher(text).matches();
        return false;
    }

    public boolean isComment(String text) {
        return matches("Comment", text);
    }

    public boolean isVariable(String text) {
        return matches("Variable", text);
    }

    public boolean isConstant(String text) {
        return matches("Constant", text);
    }
}
