package state;

/**
 * Class that maintains user defined variables within a workspace
 * Ex. GlobalVariables glvrs = new GlobalVariables();
 *     glvrs.setVariable("a", 5);
 * @author Harry Ross
 */
public class GlobalVariables {

    private VariablesGroup myVariables;

    /**
     * Creates new instance of GlobalVariables, initializes instance variable VariablesGroup
     */
    public GlobalVariables() {
        this.myVariables = new VariablesGroup();
    }

    /**
     * Returns double value of requested user variable
     * @param name String name of requested variable
     * @return Double value of requested variable
     */
    public double getVariable(String name) {
        return myVariables.getVariable(name);
    }

    /**
     * Returns VariablesGroup representation of this object
     * @return VariablesGroup representation of this object
     */
    public VariablesGroup getVariablesGroup() {
        return myVariables;
    }

    /**
     * Sets variable value within a workspace
     * @param name String name of variable to modify
     * @param value New value for requested variable
     */
    public void setVariable(String name, double value) {
        myVariables.setVariable(name, value);
    }
}
