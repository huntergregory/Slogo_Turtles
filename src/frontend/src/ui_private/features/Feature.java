package ui_private.features;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public abstract class Feature {
    private static final double VERTICAL_STRETCH_NUMBER = 1000;
    private static final String FONT_FAMILY = "verdana";
    private static final int FONT_SIZE = 12;
    private static final Font FONT = new Font(FONT_FAMILY,FONT_SIZE);
    //TODO: transfer these^ to css

    private GridPane myGrid;
    private Label myLabel;
    private boolean myHasHorizontalLayout;

    protected Feature(boolean hasHorizontalLayout) {
        myHasHorizontalLayout = hasHorizontalLayout;
    }

    /**
     * @return the main part of the feature with handlers built in
     */
    public Pane getPane(double width) {
        return getGrid(width);
    }


    /**
     * @return the main feature (selector, scrolling pane, etc.)
     */
    abstract protected Node getMainComponent();

    /**
     * @return text for label
     */
    abstract protected String getLabelText();


    /**
     * @return title describing the feature. Will be displayed near the main feature
     */
    protected Label getLabel() {
        if (myLabel != null)
            return myLabel;
        myLabel = new Label(getLabelText());
        myLabel.setFont(FONT);
        return myLabel;
    }


    private Pane getGrid(double width) {
        if (myGrid != null)
            return myGrid;
        myGrid = new GridPane();
        if (myHasHorizontalLayout)
            createHorizontalGrid();
        else
            createVerticalGrid();
        setSize(width);
        centerItems();
        return myGrid;
    }

    private void centerItems() {
        myGrid.setHalignment(getMainComponent(), HPos.CENTER);
        myGrid.setHalignment(getLabel(), HPos.CENTER);
    }

    private void createVerticalGrid() {
        myGrid.addColumn(0, getLabel(), getMainComponent());
    }

    private void createHorizontalGrid() {
        myGrid.addRow(0, getLabel(), getMainComponent());
        expandToFullWidth();
    }

    private void expandToFullWidth() {
        ColumnConstraints halfColumn = new ColumnConstraints();
        halfColumn.setPercentWidth(50);
        myGrid.getColumnConstraints().add(0, halfColumn);
        myGrid.getColumnConstraints().add(1, halfColumn);
    }

    private void setSize(double width) {
        myGrid.setMaxWidth(width);
        myGrid.setMinWidth(width);
        myGrid.setPrefHeight(VERTICAL_STRETCH_NUMBER); //stretch stuff out vertically
    }
}
