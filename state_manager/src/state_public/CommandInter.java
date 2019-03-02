package state_public;

public interface CommandInter {

    double execute();
    double runCommand();
    void addVariables(VariablesGroup variables);
    double getVariable(String variable);
}
