package control.backendapi;

import parser.InputTranslator;
import parser.ParserException;

public class LanguageCall extends BackendAPICall {

    private String newLanguage;

    public LanguageCall(String language) {
        this.newLanguage = language;
    }

    @Override
    public double call() throws ParserException {
        InputTranslator.getInstance().changeLanguage(newLanguage);
        return 0;
    }
}
