package ui_public;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class SidePanel {
    public static final double MAX_WIDTH = 500;

    private GridPane myPane;
    private ArrayList<Node> myChildren;
    private int myNumRows;

    public SidePanel() {
        myPane = new GridPane();
        myPane.setMaxWidth(0);

        myChildren = new ArrayList<>();
        myNumRows = 0;
    }

    protected void addRow(Node node) {
        myPane.addRow(myNumRows, node);
        myNumRows += 1;
    }

    public Pane getPane() {
        return myPane;
    }

    public void setWidth(double width) {
        myPane.setMinWidth(width);
        myPane.setMaxWidth(MAX_WIDTH);
    }
}
