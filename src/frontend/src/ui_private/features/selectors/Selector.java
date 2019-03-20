package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import state.StateManager;
import ui_private.features.Feature;

/**
 * @author Hunter Gregory
 */
public abstract class Selector extends Feature {
    private static final int NUM_OPTIONS_SHOWN = 4;

    ComboBox<String> myDropBox;

    public Selector(StateManager manager) {
        super(manager);
        myDropBox = new ComboBox<>();
        myDropBox.setEditable(false);
        myDropBox.itemsProperty().set(getItemList());
        myDropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        myDropBox.getSelectionModel().selectFirst();
        myDropBox.valueProperty().addListener((o, oldVal, newVal) -> handleItemSelected(newVal));
    }

    /**
     * @return Observable List that will be displayed as the options.
     */
    abstract protected ObservableList<String> getItemList();

    /**
     * @param item
     */
    abstract protected void handleItemSelected(String item);

    @Override
    public Node getMainComponent() {
        return myDropBox;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return true; }
}
