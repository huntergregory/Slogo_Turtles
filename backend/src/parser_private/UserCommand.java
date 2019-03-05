package parser_private;

import parser_private.commands.control_commands.VariableCommand;
import state_public.CommandInter;
import state_public.UserCommandInter;

import java.util.List;

public class UserCommand extends Command implements UserCommandInter {

    private CommandInter myArguments;
    private CommandInter myBody;

    public UserCommand() {}

    private UserCommand(UserCommand copy) { // COPY CONSTRUCTOR
        applyArgsAndBody(copy.myArguments, copy.myBody);
    }

    @Override
    public void assignParams(List<CommandInter> params) {
        this.mySubCommands = params;
        this.mySubCommands.add(myBody); // Since this can't happen in the constructor in this case
    }

    @Override
    public void applyArgsAndBody(CommandInter args, CommandInter body) {
        myArguments = args;
        myBody = body;
    }

    @Override
    public UserCommandInter getNewInstance() {
        return new UserCommand(this);
    }

    @Override
    public double execute() {
        int numRealParams = mySubCommands.size() - 1;
        for (int i = 0; i < Math.min(numRealParams, myArguments.size()); i++) {
            String varName = ((VariableCommand) myArguments.getParam(i)).getVariableName();
            myStateManager.getVariables().setVariable(varName, mySubCommands.get(i).execute());
        }
        return myBody.execute();
    }
}
