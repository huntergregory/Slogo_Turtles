package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import state.StateManager;
import ui_private.features.Feature;

/**
 * This abstract class defines the construction and behavior of the various types of Selectors
 * @author Hunter Gregory
 * @author Carter Gay
 */
public abstract class Selector extends Feature {
    private static final int NUM_OPTIONS_SHOWN = 4;

    ComboBox<String> myDropBox;

    /**
     * Constructor for Selector object. Creates binding between Selector and StateManager
     * @param manager
     */
    public Selector(StateManager manager) {
        super(manager);
        myDropBox = new ComboBox<>();
        myDropBox.setEditable(false);
        myDropBox.itemsProperty().set(getItemList());
        myDropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        myDropBox.getSelectionModel().selectFirst();
        myDropBox.valueProperty().addListener((o, oldVal, newVal) -> handleItemSelected(newVal));
    }

    abstract protected ObservableList<String> getItemList();

    abstract protected void handleItemSelected(String item);

    /**
     * Return the dropbox of the Selector
     * @return
     */
    @Override
    public Node getMainComponent() {
        return myDropBox;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return true; }
}
