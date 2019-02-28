package ui_private.features;

import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class VerticalFeature extends Feature {
    private static final double V_GAP = 10;

    @Override
    protected Pane getGrid(double width) {
        GridPane grid = new GridPane();
        setSizes(grid, width);
        grid.addColumn(0, getLabel(), getMainComponent());
        grid.setHalignment(getLabel(), HPos.CENTER);
        return grid;
    }

    private void setSizes(GridPane grid, double width) {
        grid.setMaxWidth(width);
        grid.setMinWidth(width);
        grid.setPrefHeight(1000); //Stretch stuff out
    }
}
