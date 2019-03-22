package ui_private.features;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import state.StateManager;
import ui_private.displays.CommandTerminal;

/**
 *
 * @author Hunter Gregory
 */
public abstract class Feature {
    private static final double VERTICAL_STRETCH_NUMBER = 1000;
    private static final String FONT_FAMILY = "verdana";
    private static final int FONT_SIZE = 12;
    private static final Font FONT = new Font(FONT_FAMILY,FONT_SIZE);

    protected StateManager myStateManager;
    private GridPane myGrid;
    private Label myLabel;
    protected CommandTerminal myCommandTerminal;

    public Feature(StateManager manager) {
        myStateManager = manager;
    }

    /**
     * @return the main part of the feature with handlers built in
     */
    public Pane getPane(double width) {
        return getGrid(width);
    }


    /**
     * @return the main feature (selector, slider, etc.)
     */
    abstract protected Node getMainComponent();

    /**
     * @return true to display feature's label to the left of its main component, false to display above
     */
    abstract protected boolean getHasHorizontalLayout();

    /**
     * Must be called before getPane
     */
    public void setLabelText(String text) {
        myLabel = new Label(text);
        myLabel.setFont(FONT);
    }


    private Pane getGrid(double width) {
        if (myGrid != null)
            return myGrid;
        myGrid = new GridPane();
        if (getHasHorizontalLayout())
            createHorizontalGrid();
        else
            createVerticalGrid();
        setSize(width);
        centerItems();
        return myGrid;
    }

    private void centerItems() {
        myGrid.setHalignment(getMainComponent(), HPos.CENTER);
        myGrid.setHalignment(myLabel, HPos.CENTER);
    }

    private void createVerticalGrid() {
        myGrid.addColumn(0, myLabel, getMainComponent());
    }

    private void createHorizontalGrid() {
        myGrid.addRow(0, myLabel, getMainComponent());
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

    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
