package state_public;

public class GlobalVariables {

    private VariablesGroup myVariables;

    public GlobalVariables() {
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
