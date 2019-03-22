package state;

import java.util.List;

/**
 * Interface for Command objects so that they may be referenced externally
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
