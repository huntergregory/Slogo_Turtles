package parser.commands.math_commands;

import parser.Command;
import parser.EvalCommand;

import java.util.List;

public abstract class SingleParamMathCommand extends EvalCommand {

    Command myExpression;

    public SingleParamMathCommand(List<Command> params) {
        super(params);
        myExpression = params.get(0);
    }
}
