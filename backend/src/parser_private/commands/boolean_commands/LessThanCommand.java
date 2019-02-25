package parser_private.commands.boolean_commands;

import parser_private.Command;

import java.util.List;

public class LessThanCommand extends TwoParamBoolCommand {

    public LessThanCommand(List<Command> params) {
        super(params);
    }

    @Override
    public double runCommand() {
        return (myExpression1.execute() < myExpression2.execute()) ? 1 : 0;
    }
}
