package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.InputTranslator;
import state_public.ParserException;

public class LanguageSelector extends Selector {
    private static final String TITLE = "Language";
    private static final ObservableList LANGUAGES =
            FXCollections.observableArrayList("Chinese", "English","French","German","Italian",
                                                    "Portuguese","Russian","Spanish","Syntax","Urdu");

    private InputTranslator myInputTranslator;

    public LanguageSelector(InputTranslator inputTranslator) {
        myInputTranslator = inputTranslator;
    }

    @Override
    protected ObservableList getItemList() {
        return LANGUAGES;
    }

    @Override
    protected void handleItemSelected(String item) {
        try {
            System.out.println("language here");
            myInputTranslator.changeLanguage(item);
        }
        catch (ParserException e) {
            System.out.println("Not a valid language");
        }
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
