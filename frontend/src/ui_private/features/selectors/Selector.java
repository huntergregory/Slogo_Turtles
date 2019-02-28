package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import ui_private.features.Feature;

public abstract class Selector<T> extends Feature {

    private static final int NUM_OPTIONS_SHOWN = 4;

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
    public Node getNode() {
        return getDropBox();
    }
}
