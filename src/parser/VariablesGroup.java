package parser;

import java.util.HashMap;
import java.util.Map;

public class VariablesGroup {

    private Map<String, Double> myVariables;

    public VariablesGroup() {
        myVariables = new HashMap<>();
    }

    double getVariable(String variable) {
        return myVariables.getOrDefault(variable, 0.0);
    }

    public void setVariable(String variable, double value) {
        myVariables.put(variable, value);
    }

    void add(VariablesGroup newVars) {
        myVariables.putAll(newVars.myVariables);
    }

    boolean hasVariable(String variable) {
        return myVariables.containsKey(variable);
    }
}
