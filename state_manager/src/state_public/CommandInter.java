package state_public;

public interface CommandInter {

    double execute();

    int size();

    CommandInter getParam(int index);

    void injectStateManager(StateManager stateManager);
}
