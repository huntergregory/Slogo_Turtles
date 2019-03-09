package state;

import java.util.List;

/**
 * @author David Miron
 * @author Harry Ross
 */
public interface ICommand {

    double execute();

    int size();

    ICommand getParam(int index);

    List<ICommand> getParams();

    void injectStateManager(StateManager stateManager);
}
