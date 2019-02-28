package ui_private.features;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public abstract class VerticalFeature extends Feature {
    private static final double V_GAP = 10;

    @Override
    protected Pane getBox() {
        GridPane grid = new GridPane();
        var label = getLabel();
        grid.setHalignment(label, HPos.CENTER);
        grid.addColumn(0, getLabel(), getMainComponent());
        return grid;
    }
}
