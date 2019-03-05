package parser_private.commands.control_commands;

import parser_private.Command;
import state_public.CommandInter;

import java.util.List;

public class MakeVariableCommand extends Command {

    private String myName;
    private CommandInter myExpression;

    public MakeVariableCommand(List<CommandInter> params) {
        super(params);
        myName = ((VariableCommand) params.get(0)).getVariableName();
        myExpression = params.get(1);
    }

    @Override
    public double execute() {
        myStateManager.getVariables().setVariable(myName, myExpression.execute());
        return myStateManager.getVariables().getVariable(myName);
    }
}
