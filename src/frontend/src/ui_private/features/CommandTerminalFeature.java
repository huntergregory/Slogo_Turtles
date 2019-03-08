package ui_private.features;

import state.StateManager;
import ui_private.displays.CommandTerminal;
import ui_private.features.selectors.Selector;

public abstract class CommandTerminalFeature extends Selector {

    protected CommandTerminal myCommandTerminal;

    public CommandTerminalFeature(StateManager manager) {
        super(manager);
    }
}
