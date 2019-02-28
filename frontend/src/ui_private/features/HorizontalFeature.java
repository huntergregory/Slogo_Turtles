package ui_private.features;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class HorizontalFeature extends Feature {
    private static final double H_GAP = 30;

    @Override
    protected Pane getBox() {
        GridPane grid = new GridPane();
        grid.addRow(0, getLabel(), getMainComponent());
        grid.setHgap(H_GAP);
        return grid;
    }
}
