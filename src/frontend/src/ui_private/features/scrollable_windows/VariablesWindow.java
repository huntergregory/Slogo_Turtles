package ui_private.features.scrollable_windows;

import state_public.StateManager;

public class VariablesWindow extends ScrollableWindow {
    private static final String TITLE = "Variables";

    public VariablesWindow(StateManager manager) {
        super(manager);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

    @Override
    public void refreshWindow() {

    }
}