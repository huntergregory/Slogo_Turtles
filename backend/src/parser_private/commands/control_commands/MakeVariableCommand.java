package parser_private.commands.control_commands;

import parser_private.Command;
import parser_public.GlobalVariables;
import java.util.List;

public class MakeVariableCommand extends Command {

    private String myName;
    private Command myExpression;

    public MakeVariableCommand(List<Command> params) {
        super(params);
        myName = ((VariableCommand) params.get(0)).getVariableName();
        myExpression = params.get(1);
    }

    @Override
    public double runCommand() {
        // --- UNCOMMENT TO ENABLE LOCAL VARIABLE SCOPE ---
        /*if (!myVariables.isEmpty()) // myVariables guaranteed to have at least 1 pre-existing variable if it's a subcommand
            myVariables.setVariable(myName, myExpression.execute()); // local scope
        else*/
            GlobalVariables.getInstance().setVariable(myName, myExpression.execute()); // global scope

        return getVariable(myName);
    }
}
