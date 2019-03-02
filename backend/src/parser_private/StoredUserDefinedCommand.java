package parser_private;

import parser_private.commands.control_commands.ListCommand;
import parser_private.commands.control_commands.VariableCommand;
import parser_public.GlobalVariables;

import java.util.Collections;
import java.util.List;

public class StoredUserDefinedCommand extends Command {

    private ListCommand myArguments;
    private ListCommand myBody;

    public StoredUserDefinedCommand(ListCommand args, ListCommand body) {
        updateArgsAndBody(args, body);
    }

    public StoredUserDefinedCommand(StoredUserDefinedCommand copy) { // COPY CONSTRUCTOR
        updateArgsAndBody(copy.myArguments, copy.myBody);
    }

    public int getArgumentCount() {
        return myArguments.size();
    }

    public void assignParams(List<Command> params) {
        this.mySubCommands = params;
        this.mySubCommands.add(myBody); // Since this can't happen in the constructor in this case
    }

    public void updateArgsAndBody(ListCommand args, ListCommand body) {
        myArguments = args;
        myBody = body;
    }

    @Override
    public double runCommand() {
        int numRealParams = mySubCommands.size() - 1;
        for (int i = 0; i < Math.min(numRealParams, myArguments.size()); i++) {
            String varName = ((VariableCommand) myArguments.getParam(i)).getVariableName();
            // --- UNCOMMENT TO ENABLE LOCAL VARIABLE SCOPE ---
            /*myVariables.setVariable(countVarName, mySubCommands.get(i).execute());
              myBody.addVariables(myVariables);*/ //propagates var changes through body commands
            GlobalVariables.getInstance().setVariable(varName, mySubCommands.get(i).execute()); // --- COMMENT THIS TO ENABLE LOCAL ---
        }
        return myBody.execute();
    }
}
