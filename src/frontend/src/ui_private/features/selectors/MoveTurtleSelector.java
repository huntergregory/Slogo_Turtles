package ui_private.features.selectors;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import state.StateManager;
import ui_private.features.Feature;
import ui_private.features.exceptions.NoFeatureException;

import java.lang.reflect.Method;

public class MoveTurtleSelector extends Feature {

    private GridPane myControlPane;

    public MoveTurtleSelector(StateManager manager) {
        super(manager);
        myControlPane = new GridPane();
        myControlPane.add(makeButton("Up"), 1, 0);
        myControlPane.add(makeButton("Left"), 0, 1);
        myControlPane.add(makeButton("Down"), 1, 1);
        myControlPane.add(makeButton("Right"), 2, 1);
    }

    private Button makeButton(String text) {
        Button b = new Button(text);
        b.setPrefSize(50, 50);
        b.setOnAction(handler -> {
            try {
                Method m = this.getClass().getDeclaredMethod(text.toLowerCase(), new Class[0]);
                m.invoke(this, new Object[0]);
            }
            catch (Exception e) {
                throw new NoFeatureException();
            }
        });
        return b;
    }

    @Override
    protected Node getMainComponent() {
        return myControlPane;
    }

    @Override
    protected boolean getHasHorizontalLayout() {
        return true;
    }

    private void up() {
        myCommandTerminal.setText("fd 50");
    }

    private void down() {
        myCommandTerminal.setText("bk 50");
    }

    private void left() {
        myCommandTerminal.setText("lt 90 fd 50");
    }

    private void right() {
        myCommandTerminal.setText("rt 90 fd 50");
    }
}
