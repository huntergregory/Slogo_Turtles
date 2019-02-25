package parser.commands.control_commands;

import parser.Command;
import parser.GlobalVariables;

import java.util.List;

public class MakeVariableCommand extends Command {

    private String myName;
    private Command myExpression;

    public MakeVariableCommand(List<Command> params) {
        super(params);
        myName = ((VariableCommand)params.get(0)).getVariableName();
        myExpression = params.get(1);
    }

    @Override
    public double runCommand() {

        if (myVariables != null)
            myVariables.setVariable(myName, myExpression.execute());
        else
            GlobalVariables.getInstance().setVariable(myName, myExpression.execute());

        return getVariable(myName);

    }
}
