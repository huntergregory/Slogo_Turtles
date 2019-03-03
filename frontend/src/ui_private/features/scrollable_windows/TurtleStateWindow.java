package ui_private.features.scrollable_windows;

public class TurtleStateWindow extends ScrollableWindow {
    private static final boolean SORTS_ALPHABETICALLY = false;
    private static final int MAX_LINES_DISPLAYED = 5;
    private static final String TITLE = "Turtle State";

    @Override
    protected int getMaxLinesDisplayed() {
        return MAX_LINES_DISPLAYED;
    }

    @Override
    protected boolean isSortedAlphabetically() {
        return SORTS_ALPHABETICALLY;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}