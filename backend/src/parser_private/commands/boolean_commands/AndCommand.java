package parser_private.commands.boolean_commands;

import parser_private.Command;
import java.util.List;

public class AndCommand extends TwoParamBoolCommand {

    public AndCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myExpression1.execute() != 0 && myExpression2.execute() != 0) ? 1 : 0;
    }
}
