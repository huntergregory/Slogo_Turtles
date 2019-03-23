package ui_private.features.scrollable_windows;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import state.StateManager;
import ui_private.features.Feature;

import java.util.LinkedList;

/**
 *
 * @author Carter Gay
 * @author Hunter Gregory
 */
abstract class ScrollableWindow extends Feature {
    private static final boolean SORTS_ALPHABETICALLY = false;
    private static final int MAX_LINES_DISPLAYED = 50;

    private HBox myHBox;
    private Button refresh;
    private ScrollPane myScrollPane;
    private TextArea myTextArea;
    private LinkedList<String> myTextChain;

    ScrollableWindow(StateManager manager) {
        super(manager);
        myHBox = new HBox();
        myTextChain = new LinkedList<>();
        myTextArea = new TextArea();
        myScrollPane = new ScrollPane(myTextArea);
        refresh = new Button("Refresh");
        refresh.setMinWidth(80);
        refresh.setOnAction((event) -> refreshWindow());
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setDisable(false);
        myHBox.getChildren().add(myScrollPane);
        myHBox.getChildren().add(refresh);
    }

    void clearText() {
        myTextArea.setText("");
    }

    void addText(String newString) {
        myTextArea.setText(newString);
    }

    abstract void refreshWindow();

    @Override
    protected Node getMainComponent() {
        return myHBox;
    }

    @Override
    protected boolean getHasHorizontalLayout() { return false; }
}
