package parser.commands.control_commands;

import parser.Command;
import parser.GlobalVariables;
import parser.commands.VariableCommand;

import java.util.List;

public class MakeVariableCommand extends Command {

    private String varName;
    private Command expr;

    public MakeVariableCommand(List<Command> params) {
        super(params);
        varName = ((VariableCommand)params.get(0)).getVariableName();
        expr = params.get(1);
    }

    @Override
    public double runCommand() {

        if (variables != null)
            variables.setVariable(varName, expr.execute());
        else
            GlobalVariables.getInstance().setVariable(varName, expr.execute());

        return getVariable(varName);

    }
}
