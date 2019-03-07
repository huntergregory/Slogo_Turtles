package ui_private.features.scrollable_windows;

import state_public.StateManager;

public class PastCommandsWindow extends ScrollableWindow {
    private static final String TITLE = "Past Commands";

    public PastCommandsWindow(StateManager manager) {
        super(manager);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}
