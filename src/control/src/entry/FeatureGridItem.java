package entry;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ui_public.UIBuilder;

public class FeatureGridItem {
    private static final int UNUSED = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final String LEFT_LABEL = "L";
    private static final String RIGHT_LABEL = "R";
    private static final String UNUSED_LABEL = " ";

    private String myText;
    private UIBuilder myWorkspace;
    private Label myLabel;
    private Button myButton;
    private int myState;

    protected FeatureGridItem(String text, UIBuilder workspace) {
        myText = text;
        myWorkspace = workspace;
        myState = 0;
        initButton();
        initLabel();
    }

    protected Label getLabel() {
        return myLabel;
    }

    protected Button getButton() {
        return myButton;
    }

    private void initLabel() {
        myLabel = new Label(myText);
        myLabel.setTextFill(Color.BLACK);
    }

    private void initButton() {
        myButton = new Button();
        myButton.setOnAction(e -> {
            incrementState();
            updateButton();
        });

        updateButton();
    }

    private void updateButton() {
        if (myState == UNUSED) {
            myButton.setText(UNUSED_LABEL);
            myWorkspace.removeFeature(myText);
        }
        else if (myState == LEFT) {
            myButton.setText(LEFT_LABEL);
            myWorkspace.addLeftFeature(myText);
        }
        else if (myState == RIGHT) {
            myButton.setText(RIGHT_LABEL);
            myWorkspace.addRightFeature(myText);
        }
    }

    private void incrementState() {
        myState += 1;
        myState %= 3;
    }
}
