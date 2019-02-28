package ui_private.features;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class VerticalFeature extends Feature {
    private static final double V_GAP = 10;

    @Override
    protected Pane getBox() {
        GridPane grid = new GridPane();
        grid.addColumn(0, getMainComponent(), getLabel());
        grid.setHgap(V_GAP);
        return grid;
    }
}
