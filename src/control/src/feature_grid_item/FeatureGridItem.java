package feature_grid_item;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import ui.UIBuilder;

/**
 * Creates a Label and Button to represent a Feature in a given workspace (UIBuilder).
 * The Button communicates with the workspace to add, remove, or shift the positioning of the feature with the given name.
 * The current position of the feature is displayed on the button.
 * @author Hunter Gregory
 */
public class FeatureGridItem {
    private static final String LEFT_TEXT = "L";
    private static final String RIGHT_TEXT = "R";
    private static final String UNUSED_TEXT = " ";

    private String myText;
    private UIBuilder myWorkspace;
    private Label myLabel;
    private Button myButton;

    /**
     * Create a FeatureGridItem
     * @param text
     * @param workspace
     */
    public FeatureGridItem(String text, UIBuilder workspace) {
        myText = text;
        myWorkspace = workspace;
        initLabel();
        initButton();
    }

    /**
     * @return Label that displays the feature name
     */
    public Label getLabel() {
        return myLabel;
    }

    /**
     * @return toggleable Button that updates and displays the feature's position in the workspace
     */
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
