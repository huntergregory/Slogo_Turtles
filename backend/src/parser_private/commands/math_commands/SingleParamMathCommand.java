package parser_private.commands.math_commands;

import parser_private.Command;
import parser_private.EvalCommand;
import java.util.List;

abstract class SingleParamMathCommand extends EvalCommand {

    Command myExpression;

    SingleParamMathCommand(List<Command> params) {
        super(params);
        myExpression = params.get(0);
    }
}
