package parser_private.commands.control_commands;

import parser_private.Command;
import parser_private.EvalCommand;
import java.util.List;

public class IfCommand extends EvalCommand {

    private Command myExpression;
    private Command myBody;

    public IfCommand(List<Command> params) {
        super(params);
        myExpression = params.get(0);
        myBody = params.get(1);
    }

    @Override
    public double runCommand() {
        if (myExpression.execute() != 0) {
            return myBody.execute();
        }
        return 0;
    }
}
