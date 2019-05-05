package ui_private.features;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import state.StateManager;
import state.Turtle;

public class ChangeTurtleImage extends Feature {

    private ScrollPane myScrollPane;
    private GridPane myGridPane;
    private int count;

    public ChangeTurtleImage(StateManager manager) {
        super(manager);
        build();
    }

    private void build() {
        buildGridPane();
        addTurtleImages();
        buildScrollPane();
    }

    private void buildGridPane() {
        myGridPane = new GridPane();
        myStateManager.getTurtleManager().getNewTurtleProperty().addListener((o, old, neww) -> addImage(neww));
    }

    private void addTurtleImages() {
        for (Turtle turtle : myStateManager.getTurtleManager().getTurtles()) {
            addImage(turtle);
            count++;
        }
    }

    private void addImage(Turtle turtle) {
        myGridPane.add(new TurtleImages(turtle).getMainComponent(), 0, count);
    }

    private void buildScrollPane() {
        myScrollPane = new ScrollPane(myGridPane);
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
