package ui_private.features.scrollable_windows;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import ui_private.features.VerticalFeature;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class ScrollableWindow extends VerticalFeature {

    private ScrollPane myScrollPane;
    private TextArea myTextArea;
    private LinkedList<String> myTextChain;

    public ScrollableWindow() {
        myTextChain = new LinkedList<>();
        myTextArea = new TextArea(getText());
        myScrollPane = new ScrollPane(myTextArea);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        myScrollPane.setDisable(true);
    }

    abstract protected int getMaxLinesDisplayed();

    abstract protected boolean isSortedAlphabetically();

    protected void addText(String newString) {
        myTextChain.addFirst(newString);
        if (isSortedAlphabetically())
            Collections.sort(myTextChain, Comparator.reverseOrder());
        myTextArea.setText(getText());
    }

    private String getText() {
        StringBuilder builder = new StringBuilder();
        int maxLines = getMaxLinesDisplayed();
        for (String line : myTextChain) {
            if (maxLines <= 0)
                break;
            builder.append(line + "\n");
            maxLines -= 1;
        }
        return builder.toString();
    }


    @Override
    protected Node getMainComponent() {
        return myScrollPane;
    }
}
