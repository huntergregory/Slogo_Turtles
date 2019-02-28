package ui_public;

import ui_private.features.scrollable_windows.ScrollableWindow;

public class UserCommandsWindow extends ScrollableWindow {
    private static final boolean SORTS_ALPHABETICALLY = false;
    private static final int MAX_LINES_DISPLAYED = 50;
    private static final String TITLE = "User Commands";

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