package entry;

import javafx.scene.control.Button;
import ui_public.UIBuilder;

public class FeatureButton {
    private static final int UNUSED = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;

    private UIBuilder myWorkspace;
    private Button myButton;
    private int myState;


    protected FeatureButton(String text, UIBuilder workspace) {
        myWorkspace = workspace;
        myState = 0;
        myButton = new Button(text);
        myButton.setOnAction(e -> updateButton());
    }

    protected Button getButton() {
        return myButton;
    }

    private void updateButton() {
        incrementState();
        if (myState == UNUSED) {
        }
        else if (myState == LEFT) {

        }
        else if (myState == RIGHT) {

        }
    }

    private void incrementState() {
        myState += 1;
        myState %= 3;
    }
}
