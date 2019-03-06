package state_public;

import java.util.List;

public interface UserCommandInter extends CommandInter {

    void assignParams(List<CommandInter> params);

    void applyArgsAndBody(CommandInter args, CommandInter Body);

    int size();

    UserCommandInter getNewInstance();
}
