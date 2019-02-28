package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import ui_private.features.Feature;

public abstract class Selector extends Feature {

    private static final int NUM_OPTIONS_SHOWN = 4;

    public Selector() {
        ComboBox<?> dropBox = getDropBox();
        dropBox.setEditable(true);
        dropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        dropBox.setItems(getItemList());
        dropBox.getSelectionModel().selectFirst();
    }


    abstract protected ObservableList getItemList();


    /**
     * @return nonnull ComboBox
     */
    abstract protected ComboBox<?> getDropBox();


    @Override
    public Node getNode() {
        return getDropBox();
    }
}
