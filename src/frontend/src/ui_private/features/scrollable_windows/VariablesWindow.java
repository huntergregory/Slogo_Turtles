package ui_private.features.scrollable_windows;

import state.StateManager;

import java.util.Map;

/**
 *
 * @author Carter Gay
 */
class VariablesWindow extends ScrollableWindow {
    private static final String TITLE = "Variables";
    private Map<String, Double> myVariablesMap;

    VariablesWindow(StateManager manager) {
        super(manager);
    }

    private void getVariables() {
        clearText();
        myVariablesMap = myStateManager.getVariables().getVariablesGroup().getMap();
        String myVariable = "";
        for (Map.Entry<String,Double> entry : myVariablesMap.entrySet()) {
            myVariable += ( entry.getKey() + "=" + entry.getValue() + "\n");
            addText(myVariable);
        }
    }

    @Override
    void refreshWindow() {
        getVariables();
    }

}