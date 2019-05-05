package ui_private.features.addition;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import state.StateManager;
import state.Turtle;
import ui_private.features.Feature;

public class TurtleImagesFeature extends Feature {
    private static final double V_GAP = 10;
    private static final double H_GAP = 10;
    private static final int COLUMNS = 3;

    private int finalIconIndex;
    private ScrollPane myScrollPane;
    private GridPane myGridPane;

    public TurtleImagesFeature(StateManager manager) {
        super(manager);
        initGridPane();
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles())
            addIcon(turtle);
        myScrollPane = new ScrollPane(myGridPane);
    }

    private void initGridPane() {
        myGridPane = new GridPane();
        myGridPane.setVgap(V_GAP);
        myGridPane.setHgap(H_GAP);
        myStateManager.getTurtleManager().getNewTurtleProperty().addListener((o, old, neww) -> addIcon(neww));
    }

    private void addIcon(Turtle turtle) {
        myGridPane.add(new TurtleIcon(turtle).getIcon(), finalIconIndex % COLUMNS, finalIconIndex / COLUMNS);
        finalIconIndex ++;
    }

    @Override
    protected Node getMainComponent() {
        return myScrollPane;
    }

    @Override
    protected boolean getHasHorizontalLayout() {
        return false;
    }
}
