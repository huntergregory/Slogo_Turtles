package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import state.StateManager;
import ui_private.features.Feature;
import ui_private.displays.CommandTerminal;

public abstract class Selector extends Feature {
    private static final int NUM_OPTIONS_SHOWN = 4;

    private ComboBox<String> myDropBox;
    protected CommandTerminal myCommandTerminal;

    public Selector(StateManager manager) {
        super(manager);
        myDropBox = new ComboBox<>();
        myDropBox.setEditable(false);
        myDropBox.itemsProperty().set(getItemList());
        myDropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        myDropBox.getSelectionModel().selectFirst();
        myDropBox.valueProperty().addListener((o, oldVal, newVal) -> handleItemSelected(newVal));
        //String selectedItem = myDropBox.getSelectionModel().getSelectedItem();
        //myDropBox.setOnAction(e -> handleItemSelected(selectedItem));
    }

    /**
     * @return Observable List that will be displayed as the options.
     */
    abstract protected ObservableList<String> getItemList();

    /**
     * @param item
     */
    abstract protected void handleItemSelected(String item);

    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    };

    @Override
    public Node getMainComponent() {
        return myDropBox;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return true; }
}
