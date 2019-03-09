package state;

import java.util.List;

/**
 * @author Harry Ross
 * @author David Miron
 */
public interface IUserCommand extends ICommand {

    void assignParams(List<ICommand> params);

    void applyArgsAndBody(ICommand args, ICommand Body);

    int size();

    IUserCommand getNewInstance();
}
