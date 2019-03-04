package state_public;

import java.util.List;

public interface UserDefinedCommandInter extends CommandInter {
    int getArgumentCount();
    void assignParams(List<CommandInter> params);
    void updateArgsAndBody(CommandInter args, CommandInter Body);
    UserDefinedCommandInter getNewInstance();
}
