package parser.commands.control_commands;

import parser.Command;
import parser.EvalCommand;
import parser.VariablesGroup;
import java.util.List;

public class RepeatCommand extends EvalCommand {

    private Command myTotalIter;
    private Command myBody;
    private Command myCurrentIter; //TODO  :repcount should be global???????? Overwritten in relevant parts of code??

    public RepeatCommand(List<Command> params) {
        super(params);
        myTotalIter = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        int limit = (int) myTotalIter.execute();
        String countVarName = "repcount";

        for (int i = 1; i < limit; i++) { //TODO remove duplication
            VariablesGroup vars = new VariablesGroup();
            vars.setVariable(countVarName, i);
            myBody.setVariables(vars);
            myBody.execute();
        }
        VariablesGroup vars = new VariablesGroup();
        vars.setVariable(countVarName, limit);
        myBody.setVariables(vars);
        return myBody.execute(); // Final iteration
    }
}
