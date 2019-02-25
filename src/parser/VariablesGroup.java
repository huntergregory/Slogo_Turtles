package parser;

import java.util.HashMap;
import java.util.Map;

public class VariablesGroup {

    private Map<String, Double> variables;

    public VariablesGroup() {
        variables = new HashMap<>();
    }

    double getVariable(String variable) {
        return variables.getOrDefault(variable, 0.0);
    }

    public void setVariable(String variable, double value) {
        variables.put(variable, value);
    }

    boolean hasVariable(String variable) {
        return variables.containsKey(variable);
    }
}
