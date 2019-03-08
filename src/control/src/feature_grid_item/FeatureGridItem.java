package feature_grid_item;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ui_public.UIBuilder;

public class FeatureGridItem {
    private static final String LEFT_TEXT = "L";
    private static final String RIGHT_TEXT = "R";
    private static final String UNUSED_TEXT = " ";

    private String myText;
    private UIBuilder myWorkspace;
    private Label myLabel;
    private Button myButton;

    public FeatureGridItem(String text, UIBuilder workspace) {
        myText = text;
        myWorkspace = workspace;
        initLabel();
        initButton();
    }

    public Label getLabel() {
        return myLabel;
    }

    public Button getButton() {
        return myButton;
    }

    private void initLabel() {
        myLabel = new Label(myText);
        myLabel.setTextFill(Color.BLACK);
    }

    private void initButton() {
        myButton = new Button();
        updateText();

        myButton.setOnAction(e -> {
            updateFeatureLocation();
            updateText();
        });
    }

    private void updateText() {
        if (isRight())
            myButton.setText(RIGHT_TEXT);
        else if (isLeft())
            myButton.setText(LEFT_TEXT);
        else
            myButton.setText(UNUSED_TEXT);
    }

    private void updateFeatureLocation() {
        if (isRight())
            myWorkspace.removeFeature(myText);
        else if (isLeft())
            myWorkspace.addRightFeature(myText);
        else
            myWorkspace.addLeftFeature(myText);
    }

    private boolean isRight() {
        return textIsWithin(myWorkspace.getRightFeatures());
    }

    private boolean isLeft() {
        return textIsWithin(myWorkspace.getLeftFeatures());
    }


    private boolean textIsWithin(String[] array) {
        for (String w : array) {
            if (w.equals(myText))
                return true;
        }
        return false;
    }
}
