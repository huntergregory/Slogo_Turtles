package state_public;

import java.util.HashMap;
import java.util.Map;

public class VariablesGroup {

    private Map<String, Double> myVariableMap;

    public VariablesGroup() {
        myVariableMap = new HashMap<>();
    }

    public double getVariable(String variable) {
        return myVariableMap.getOrDefault(variable, 0.0);
    }

    public void setVariable(String variable, double value) {
        myVariableMap.put(variable, value);
    }

    public boolean isEmpty() { //TODO
        return myVariableMap.isEmpty();
    }

    public boolean hasVariable(String variable) {
        return myVariableMap.containsKey(variable);
    }

    public Map<String, Double> getMap() {
        return myVariableMap;
    }
}
