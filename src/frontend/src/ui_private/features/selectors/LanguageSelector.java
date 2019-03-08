package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.InputTranslator;
import state_public.ParserException;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class LanguageSelector extends Selector {
    private static final String TITLE = "Language";
    private static final ObservableList LANGUAGES =
            FXCollections.observableArrayList("Chinese", "English","French","German","Italian",
                                                    "Portuguese","Russian","Spanish","Syntax","Urdu");

    public LanguageSelector(StateManager manager) {
        super(manager);
        setItemList(LANGUAGES);
    }

    @Override
    protected void handleItemSelected(String item) {
        try {
            System.out.println("language here");
            myStateManager.getInputTranslator().changeLanguage(item);
        }
        catch (ParserException e) {
            System.out.println("Not a valid language");
        }
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
