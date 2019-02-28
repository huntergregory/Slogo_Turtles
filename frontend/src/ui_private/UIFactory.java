package ui_private;

import javafx.scene.layout.Pane;
import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.*;
import ui_private.features.exceptions.NoFeatureException;

public class UIFactory {
    private TurtleDisplay myTurtleDisplay;
    private SidePanel myLeftPanel;
    private CommandTerminal myTerminal;
    private SidePanel myRightPanel;
    private double mySidePanelWidth;

    public UIFactory(double width, double height) {
        mySidePanelWidth = width / 3.0;
        var turtlePanelWidth = width / 3.0;
        var turtlePaneHeight = height * 2.0 / 3.0;
        var terminalHeight = height / 6.0;
        myTerminal = new CommandTerminal(); //FIXME
        myTurtleDisplay = new TurtleDisplay(turtlePanelWidth, turtlePaneHeight);
        myLeftPanel = new SidePanel(mySidePanelWidth);
        myRightPanel = new SidePanel(mySidePanelWidth);
    }

    public Pane getLeft() {
        return myLeftPanel.getPane();
    }

    public Pane getRight() {
        return myRightPanel.getPane();
    }

    public Pane getBottom() {
        return myTerminal.getPane();
    }

    public Pane getCenter() {
        return myTurtleDisplay.getPane();
    }

    public void addFeature(FeatureType type) {
        try {
            var feature = type.getFeature();
            if (feature instanceof VerticalFeature)
                addVerticalFeature((VerticalFeature) feature);
            else
                addHorizontalFeature((HorizontalFeature) feature);
        }
        catch (NoFeatureException e) {
            System.out.println("Failed to add feature, update the FeatureType enum.");
        }
    }

    //vertical features currently consist of only scrollable windows
    private void addVerticalFeature(VerticalFeature feature) {
        myRightPanel.addRow(feature.getPane(mySidePanelWidth));
    }

    //horizontal features currently consist of Selectors and ColorChoosers
    private void addHorizontalFeature(HorizontalFeature feature) {
        myLeftPanel.addRow(feature.getPane(mySidePanelWidth));
    }
}
