package ui_private.features.scrollable_windows;

import state_public.StateManager;

import java.util.Map;
import java.util.HashMap;

public class VariablesWindow extends ScrollableWindow {
    private static final String TITLE = "Variables";
    private Map<String, Double> myVariablesMap;

    public VariablesWindow(StateManager manager) {
        super(manager);
    }

    protected void getVariables() {
        clearText();
        myVariablesMap = myStateManager.getVariables().getVariablesGroup().getMap();
        String myVariable = "";
        for (Map.Entry<String,Double> entry : myVariablesMap.entrySet()) {
            myVariable += ( entry.getKey() + "=" + entry.getValue() + "\n");
            addText(myVariable);
        }
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }

    @Override
    public void refreshWindow() {
        getVariables();
    }

}