package state_public;

import java.util.List;

public interface IUserCommand extends ICommand {

    void assignParams(List<ICommand> params);

    void applyArgsAndBody(ICommand args, ICommand Body);

    int size();

    IUserCommand getNewInstance();
}
