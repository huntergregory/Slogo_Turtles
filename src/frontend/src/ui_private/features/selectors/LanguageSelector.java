package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.ParserException;
import state.StateManager;

/**
 * @author Carter Gay
 * @author Harry Ross
 */
public class LanguageSelector extends Selector {

    public LanguageSelector(StateManager manager) {
        super(manager);
        myDropBox.getSelectionModel().select("English");
    }

    @Override
    protected ObservableList<String> getItemList() {
        return myStateManager.getInputTranslator().getLanguages();
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
}
