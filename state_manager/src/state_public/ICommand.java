package state_public;

import java.util.List;

public interface ICommand {

    double execute();

    int size();

    ICommand getParam(int index);

    List<ICommand> getParams();

    void injectStateManager(StateManager stateManager);
}
