package state_public;

import java.util.List;

public interface UserCommandInter extends ICommand {

    void assignParams(List<ICommand> params);

    void applyArgsAndBody(ICommand args, ICommand Body);

    int size();

    UserCommandInter getNewInstance();
}
