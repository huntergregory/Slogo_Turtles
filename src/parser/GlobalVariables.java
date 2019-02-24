package parser;

import parser.commands.math_commands.ConstantCommand;

public class GlobalVariables {

    private static GlobalVariables instance;

    private VariablesGroup variables;

    private GlobalVariables() {
        this.variables = new VariablesGroup();
    }

    public static GlobalVariables getInstance() {
        if (instance == null)
            instance = new GlobalVariables();
        return instance;
    }


    public double getVariable(String name) {
        return variables.getVariable(name);
    }

    public void setVariable(String name, double value) {
        variables.setVariable(name, value);
    }

}
