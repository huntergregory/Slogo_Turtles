package parser_public.api;

import parser_public.InputTranslator;
import parser_public.ParserException;

public class LanguageCall {

    private String newLanguage;

    public LanguageCall(String language) {
        this.newLanguage = language;
    }

    public void call() throws ParserException {
        InputTranslator.getInstance().changeLanguage(newLanguage);
    }
}
