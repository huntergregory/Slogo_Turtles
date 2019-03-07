package ui_private.features.scrollable_windows;

import ui_private.features.scrollable_windows.ScrollableWindow;

public class PastCommandsWindow extends ScrollableWindow {
    private static final String TITLE = "Past Commands";

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
