package state_public;

import java.util.List;

public interface CommandInter {

    double execute();

    int size();

    CommandInter getParam(int index);

    List<CommandInter> getParams();

    void injectStateManager(StateManager stateManager);
}
