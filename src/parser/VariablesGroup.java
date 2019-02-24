package parser;

import parser.commands.math_commands.ConstantCommand;

import java.util.HashMap;
import java.util.Map;

public class VariablesGroup {

    private Map<String, Double> variables;

    public VariablesGroup() {
        variables = new HashMap<>();
    }

    public double getVariable(String variable) {
        return variables.getOrDefault(variable, 0.0);
    }

    public void setVariable(String variable, double value) {
        variables.put(variable, value);
    }

    public boolean hasVariable(String variable) {
        return variables.containsKey(variable);
    }
}
