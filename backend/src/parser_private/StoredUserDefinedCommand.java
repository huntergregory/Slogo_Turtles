package parser_private;

import parser_private.commands.control_commands.ListCommand;
import parser_private.commands.control_commands.VariableCommand;
import state_public.CommandInter;
import state_public.UserDefinedCommandInter;

import java.util.List;

public class StoredUserDefinedCommand extends Command implements UserDefinedCommandInter {

    private CommandInter myArguments;
    private CommandInter myBody;

    public StoredUserDefinedCommand() {}

    private StoredUserDefinedCommand(StoredUserDefinedCommand copy) { // COPY CONSTRUCTOR
        applyArgsAndBody(copy.myArguments, copy.myBody);
    }

    @Override
    public void assignParams(List<CommandInter> params) {
        this.mySubCommands = params;
        this.mySubCommands.add(myBody); // Since this can't happen in the constructor in this case
    }

    public void applyArgsAndBody(CommandInter args, CommandInter body) {
        myArguments = args;
        myBody = body;
    }

    public UserDefinedCommandInter getNewInstance() {
        return new StoredUserDefinedCommand(this);
    }

    @Override
    public double runCommand() {
        int numRealParams = mySubCommands.size() - 1;
        for (int i = 0; i < Math.min(numRealParams, myArguments.size()); i++) {
            String varName = ((VariableCommand) myArguments.getParam(i)).getVariableName();
            // --- UNCOMMENT TO ENABLE LOCAL VARIABLE SCOPE ---
            /*myVariables.setVariable(countVarName, mySubCommands.get(i).execute());
              myBody.addVariables(myVariables);*/ //propagates var changes through body commands
            myStateManager.getVariables().setVariable(varName, mySubCommands.get(i).execute()); // --- COMMENT THIS TO ENABLE LOCAL ---
        }
        return myBody.execute();
    }
}
