package state_public;

public interface CommandInter {

    double execute();
    double runCommand();
    void addVariables(VariablesGroup variables);
    double getVariable(String variable);
    int size();
    CommandInter getParam(int index);
    void injectStateManager(StateManager stateManager);
}
