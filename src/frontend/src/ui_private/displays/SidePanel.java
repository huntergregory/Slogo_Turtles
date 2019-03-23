package ui_private.displays;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * This class represents a side panel that any Node can be added to.
 * The side panel collapses if no items are contained in it.
 * @author Carter Gay
 * @author Hunter Gregory
 */
public class SidePanel {
    public static final double V_GAP = 30;
    private static final Color GREEN = Color.web("0x027a50");
    private static final Background BACKGROUND = new Background(new BackgroundFill(GREEN, null, null));

    private GridPane myPane;
    private ArrayList<Node> myChildren;
    private double myNormalWidth;
    private int myNumRows;

    /**
     * Create a SidePanel
     * @param width
     */
    public SidePanel(double width) {
        initializeGridPane();
        myNormalWidth = width;
        myChildren = new ArrayList<>();
        myNumRows = 0;
        updatePanelSize();
    }

    /**
     * @return the Pane representation of the panel
     */
    public Pane getPane() {
        return myPane;
    }

    /**
     * Add a Node to the panel
     * @param node
     */
    public void addRow(Node node) {
        myPane.addRow(myNumRows, node);
        myChildren.add(node);
        myNumRows += 1;
        updatePanelSize();
    }

    /**
     * Remove a Node from the panel
     * @param index
     */
    public void removeRow(int index) {
        myPane.getChildren().remove(index);
        myNumRows -= 1;
        updatePanelSize();
    }


    private void initializeGridPane() {
        myPane = new GridPane();
        myPane.setMaxWidth(0);
        myPane.setVgap(V_GAP);
        myPane.setBackground(BACKGROUND);
    }


    private void updatePanelSize() {
        if (myNumRows >= 1)
            expandPanel();
        else
            shrinkPanel();
    }

    private void expandPanel() {
        myPane.setMinWidth(myNormalWidth);
        myPane.setMaxWidth(myNormalWidth);
    }

    private void shrinkPanel() {
        myPane.setMinWidth(0);
        myPane.setMaxWidth(0);
    }
}
