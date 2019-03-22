package state;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that maintains a group of user defined variables
 * @author Harry Ross
 */
public class VariablesGroup {

    private Map<String, Double> myVariableMap;

    /**
     * Creates a new VariablesGroup object and initializes Map of variables
     */
    public VariablesGroup() {
        myVariableMap = new HashMap<>();
    }

    /**
     * Returns double value of variable
     * @param variable String name of variable
     * @return Double value of variable or 0.0 if not found
     */
    public double getVariable(String variable) {
        return myVariableMap.getOrDefault(variable, 0.0);
    }

    /**
     * Sets given variable string to given double value
     * @param variable String name of variable
     * @param value Double value being set to given variable
     */
    public void setVariable(String variable, double value) {
        myVariableMap.put(variable, value);
    }

    /**
     * Checks to determine if this VariablesGroup has any members
     * @return True if no variables have been declared
     */
    public boolean isEmpty() {
        return myVariableMap.isEmpty();
    }

    /**
     * Checks to determine if given variable String corresponds with a value that is defined
     * @param variable String name of variable
     * @return True if variable is defined
     */
    public boolean hasVariable(String variable) {
        return myVariableMap.containsKey(variable);
    }

    /**
     * Returns Map representation of VariablesGroup
     * @return Map of variable name to value
     */
    public Map<String, Double> getMap() {
        return myVariableMap;
    }
}
