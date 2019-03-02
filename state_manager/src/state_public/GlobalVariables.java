package state_public;

import parser_private.VariablesGroup;

public class GlobalVariables {

    private static GlobalVariables instance;
    private VariablesGroup myVariables;

    private GlobalVariables() {
        this.myVariables = new VariablesGroup();
    }

    public double getVariable(String name) {
        return myVariables.getVariable(name);
    }

    public VariablesGroup getVariablesGroup() {
        return myVariables;
    }

    public void setVariable(String name, double value) {
        myVariables.setVariable(name, value);
    }
}
