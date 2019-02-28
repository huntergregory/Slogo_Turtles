package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import ui_private.features.HorizontalFeature;

public abstract class Selector<T> extends HorizontalFeature {

    private static final int NUM_OPTIONS_SHOWN = 4;

    private boolean myWasUsed = false;

    public Selector() {
        ComboBox<T> dropBox = getDropBox();
        dropBox.setEditable(true);
        dropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        dropBox.setItems(getItemList());
        dropBox.getSelectionModel().selectFirst();
        T selectedItem = dropBox.getSelectionModel().getSelectedItem();
        dropBox.setOnAction(handleItemSelected(selectedItem));
    }

    /**
     * @return nonnull ComboBox
     */
    abstract protected ComboBox<T> getDropBox();


    /**
     * @return List of selectable items
     */
    abstract protected ObservableList getItemList();

    /**
     * Make sure Object is the
     * @param item
     * @return
     */
    abstract protected EventHandler<ActionEvent> handleItemSelected(T item);


    @Override
    public Node getMainComponent() {
        return getDropBox();
    }
}
