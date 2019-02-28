package ui_private.features;

import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ui_private.displays.TurtleDisplay;

public abstract class HorizontalFeature extends Feature {
    private static final double H_GAP = 20;

    protected TurtleDisplay myTurtleDisplay;

    @Override
    protected Pane getGrid(double width) {
        GridPane grid = new GridPane();
        setSizes(grid, width);
        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(50);
        grid.getColumnConstraints().add(labelColumn);
        var component = getMainComponent();
        grid.addRow(0, getLabel(), component);
        grid.setHalignment(component, HPos.RIGHT);
        grid.setHgap(H_GAP);
        return grid;
    }

    private void setSizes(GridPane grid,  double width) {
        grid.setMaxWidth(width);
        grid.setMinWidth(width);
        grid.setPrefHeight(1000); //Stretch stuff out
    }

    public void setTurtleDisplay(TurtleDisplay turtleDisplay) {
        if (turtleDisplay == null)
            return;
        myTurtleDisplay = turtleDisplay;
    }
}
