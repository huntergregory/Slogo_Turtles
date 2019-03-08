package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.ParserException;
import state.StateManager;
import ui_private.displays.CommandTerminal;

public class LanguageSelector extends Selector {
    private static final ObservableList LANGUAGES =
            FXCollections.observableArrayList("Chinese", "English","French","German","Italian",
                                                    "Portuguese","Russian","Spanish","Urdu");

    public LanguageSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return LANGUAGES;
    }

    @Override
    protected void handleItemSelected(String item) {
        try {
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
}
