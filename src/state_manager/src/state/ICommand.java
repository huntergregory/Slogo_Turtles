package state;

import java.util.List;

/**
 * @author David Miron
 */
public interface ICommand {

    double execute();

    int size();

    ICommand getParam(int index);

    List<ICommand> getParams();

    void injectStateManager(StateManager stateManager);
}
