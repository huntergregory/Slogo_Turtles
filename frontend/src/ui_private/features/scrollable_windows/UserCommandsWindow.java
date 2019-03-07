package ui_public;

import ui_private.features.scrollable_windows.ScrollableWindow;

public class UserCommandsWindow extends ScrollableWindow {
    private static final String TITLE = "User Commands";

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}