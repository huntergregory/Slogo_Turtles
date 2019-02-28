package ui_private.features;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ui_private.features.exceptions.NodeAlreadyUsedException;

public abstract class Feature {
    private static final String FONT_FAMILY = "verdana";
    private static final int FONT_SIZE = 12;
    private static final Font FONT = new Font(FONT_FAMILY,FONT_SIZE);
    //TODO: transfer these^ to css

    private Label myLabel;
    private boolean myWasUsed = false;

    /**
     * @return the main part of the feature with handlers built in
     */
    public Pane getPane(double width) throws NodeAlreadyUsedException {
        if (myWasUsed)
            throw new NodeAlreadyUsedException();
        myWasUsed = true;
        return getGrid(width);
    }

    /**
     * @return a Pane which another abstract class out to create using getMainComponent and getLabel
     */
    abstract protected Pane getGrid(double width);

    /**
     * @return the main feature (selector, scrolling pane, etc.)
     */
    abstract protected Node getMainComponent();

    /**
     * @return title describing the feature. Will be displayed near the main feature
     */
    protected Label getLabel() {
        if (myLabel != null)
            return myLabel;
        myLabel = new Label(getLabelText());
        myLabel.setFont(FONT);
        return myLabel;
    }

    /**
     * @return text for label
     */
    abstract protected String getLabelText();
}
