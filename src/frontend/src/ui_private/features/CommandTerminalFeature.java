package ui_private.features;

import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public abstract class CommandTerminalFeature extends Feature {

    protected CommandTerminal myCommandTerminal;

    public CommandTerminalFeature(StateManager manager) {
        super(manager);
    }

    /**
     * Must be called right after the constructor to avoid null pointer exceptions.
     * @param terminal
     */
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}
