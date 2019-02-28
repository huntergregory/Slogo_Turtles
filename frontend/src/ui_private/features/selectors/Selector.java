package ui_private.features.selectors;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Font;
import ui_private.features.Feature;

public abstract class Selector extends Feature {
    private static final String FONT_FAMILY = "verdana";
    private static final int FONT_SIZE = 12;
    private static final Font FONT = new Font(FONT_FAMILY,FONT_SIZE);
    //TODO: transfer these^ to css

    private static final int NUM_OPTIONS_SHOWN = 4;

    public Selector() {
        ComboBox<?> dropBox = getDropBox();
        dropBox.setPromptText(dropBoxText);
        dropBox.setEditable(true);
        dropBox.setVisibleRowCount(NUM_OPTIONS_SHOWN);
        dropBox.setItems(list);
    }

    /**
     * @return nonnull ComboBox
     */
    abstract protected ComboBox<?> getDropBox();

    @Override
    public Node getNode() {
        return getDropBox();
    }
}
