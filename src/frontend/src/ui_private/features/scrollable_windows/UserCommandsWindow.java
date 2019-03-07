package ui_private.features.scrollable_windows;

import state_public.StateManager;

public class UserCommandsWindow extends ScrollableWindow {
    private static final String TITLE = "User Commands";

    public UserCommandsWindow(StateManager manager) {
        super(manager);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}