package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import parser_public.InputTranslator;
import parser_public.ParserException;

public class LanguageSelector extends Selector {
    private static final String TITLE = "Language";
    private static final ObservableList LANGUAGES =
            FXCollections.observableArrayList("Chinese", "English","French","German","Italian",
                                                    "Portuguese","Russian","Spanish","Syntax","Urdu");

    @Override
    protected ObservableList getItemList() {
        return LANGUAGES;
    }

    @Override
    protected void handleItemSelected(String item) {
        try {
            System.out.println("language here");
            InputTranslator.getInstance().changeLanguage(item);
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
