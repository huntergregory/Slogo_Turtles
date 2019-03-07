package ui_private.features.scrollable_windows;

import state_public.StateManager;

public class TurtleStateWindow extends ScrollableWindow {
    private static final String TITLE = "Turtle State";

    public TurtleStateWindow(StateManager manager) {
        super(manager);
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}