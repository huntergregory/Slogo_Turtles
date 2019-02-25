package control.backendapi;

import parser.InputTranslator;
import parser.ParserException;

public class LanguageCall {

    private String newLanguage;

    public LanguageCall(String language) {
        this.newLanguage = language;
    }

    public void call() throws ParserException {
        InputTranslator.getInstance().changeLanguage(newLanguage);
    }
}
