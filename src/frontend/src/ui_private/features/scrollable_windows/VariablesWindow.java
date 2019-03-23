package ui_private.features.scrollable_windows;

import state.StateManager;

import java.util.Map;

/**
 * This class is used to view environment variables
 * @author Carter Gay
 */
public class VariablesWindow extends ScrollableWindow {
    private static final String TITLE = "Variables";
    private Map<String, Double> myVariablesMap;

    /**
     * Creates binding between VariablesWindow and StateManager
     * @param manager
     */
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

    /**
     * Update the variables window
     */
    @Override
    public void refreshWindow() {
        getVariables();
    }

}