package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SidePanel {
    public static final double MAX_WIDTH = 500;
    public static final double V_GAP = 30;
    private static final Color GREEN = Color.web("0x027a50");
    private static final Background BACKGROUND = new Background(new BackgroundFill(GREEN, null, null));

    private GridPane myPane;
    private ArrayList<Node> myChildren;
    private double myNormalWidth;
    private int myNumRows;


    public SidePanel(double width) {
        initializeGridPane();
        myNormalWidth = (MAX_WIDTH < width) ? MAX_WIDTH : width;
        myChildren = new ArrayList<>();
        myNumRows = 0;
    }


    public void addRow(Node node) {
        if (myNumRows == 0)
            expandPanel();
        myPane.addRow(myNumRows, node);
        myChildren.add(node);
        myNumRows += 1;
    }


    public Pane getPane() {
        return myPane;
    }


    private void initializeGridPane() {
        myPane = new GridPane();
        myPane.setMaxWidth(0);
        myPane.setVgap(V_GAP);
        myPane.setBackground(BACKGROUND);
    }


    private void expandPanel() {
        myPane.setMinWidth(myNormalWidth);
        myPane.setMaxWidth(MAX_WIDTH);
    }
}
