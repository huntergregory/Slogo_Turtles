package entry;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ui_public.FeatureType;
import ui_public.UIBuilder;

public class FeatureGridItem {
    private static final int UNUSED = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;

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
            myButton.setText(" ");
            myButton.setBackground(getBackground(Color.WHITE));
        }
        else if (myState == LEFT) {
            myButton.setText("L");
            myButton.setBackground(getBackground(Color.BLUE));
            myWorkspace.addLeftFeature(FeatureType.USER_COMMANDS_SELECTOR); //FIXME
        }
        else if (myState == RIGHT) {
            myButton.setText("R");
            myButton.setBackground(getBackground(Color.RED));
            myWorkspace.addRightFeature(FeatureType.USER_COMMANDS_SELECTOR); //FIXME
        }
    }

    private Background getBackground(Color color) {
        return new Background(new BackgroundFill(color, null, null));
    }

    private void incrementState() {
        myState += 1;
        myState %= 3;
    }
}
