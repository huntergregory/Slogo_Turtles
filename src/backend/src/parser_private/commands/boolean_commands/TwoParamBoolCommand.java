package parser_private.commands.boolean_commands;

import parser_private.Command;
import state_public.ICommand;

import java.util.List;

abstract class TwoParamBoolCommand extends Command {

    ICommand myExpression1;
    ICommand myExpression2;

    TwoParamBoolCommand(List<ICommand> params) {
        super(params);
        this.myExpression1 = params.get(0);
        this.myExpression2 = params.get(1);
    }

    abstract public double execute();
}
