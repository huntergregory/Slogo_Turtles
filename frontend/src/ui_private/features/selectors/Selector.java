package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import ui_private.features.HorizontalFeature;

public abstract class Selector<T> extends HorizontalFeature {
    private static final int NUM_OPTIONS_SHOWN = 4;

    private ComboBox<T> myDropBox;

    public Selector() {
        myDropBox = new ComboBox<>();
        myDropBox.setEditable(false);
        myDropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        myDropBox.setItems(getItemList());
        myDropBox.getSelectionModel().selectFirst();
        T selectedItem = myDropBox.getSelectionModel().getSelectedItem();
        myDropBox.setOnAction(handleItemSelected(selectedItem));
    }


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
        return myDropBox;
    }
}
