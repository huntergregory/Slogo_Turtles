package state_public;

import java.util.List;

public interface UserDefinedCommandInter extends CommandInter {
    void assignParams(List<CommandInter> params);
    void applyArgsAndBody(CommandInter args, CommandInter Body);
    UserDefinedCommandInter getNewInstance();
}
